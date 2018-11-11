package com.tony.albanese.mynews.controller.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.activities.WebViewActivity
import com.tony.albanese.mynews.controller.adapters.ArticleRecyclerAdapter
import com.tony.albanese.mynews.controller.utilities.*
import com.tony.albanese.mynews.model.Article
import kotlinx.android.synthetic.main.fragment_base_layout.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.HttpURLConnection
import java.util.*


/*
This class will serve as the basis for the other article fragments in order to reduce the repeated code.
 */

class ArticleFragment : Fragment() {
    lateinit var swipeLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var articlePreferences: SharedPreferences
    lateinit var articleAdapter: ArticleRecyclerAdapter

    lateinit var url: String
    lateinit var articlesType: String
    lateinit var prefArticleKey: String

    var searchType = 0
    var resultType = 0



    var list = ArrayList<Article>()
    var tempList = ArrayList<Article>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_layout, container, false)
        swipeLayout = view.findViewById(R.id.swipe_refresh_layout)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let { setValuesFromBundle(it) }

        val layoutManager = LinearLayoutManager(context)
        val subjectView = text_view_subject

        recyclerView = fragment_recycler_view
        articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = layoutManager

        initializeArticleArray()

        subjectView.text = articlesType

        swipeLayout.setOnRefreshListener {
            startSearch()
        }
    }

    //This function is calle when the user clicks an article.
    fun onArticleClicked(view: View, article: Article) {
        view.setBackgroundColor(resources.getColor(R.color.colorIsRead))
        article.mIsRead = true
        val intent = Intent(context, WebViewActivity::class.java).apply {
            putExtra(URL_EXTRA_KEY, article.mUrl)
        }
        startActivity(intent)
    }

    //This function will set the parameters for a new fragment.
    fun newInstance(searchType: Int, resultType: Int, articlePreferencesKey: String): ArticleFragment {
        val bundle = Bundle()
        bundle.putInt("search_type", searchType)
        bundle.putInt("result_type", resultType)
        bundle.putString("pref_key", articlePreferencesKey)

        val fragment = ArticleFragment()
        fragment.setArguments(bundle)
        return fragment
    }

    fun setValuesFromBundle(bundle: Bundle) {
        searchType = bundle.getInt("search_type")
        resultType = bundle.getInt("result_type")
        prefArticleKey = bundle.getString("pref_key")

        url = generateSearchUrl(context!!, searchType)
        articlePreferences = activity!!.getSharedPreferences(ARTICLE_PREFERENCES, Context.MODE_PRIVATE)

        when (searchType) {
            TOP_STORIES_SEARCH -> articlesType = "Top Stories"
            TOP_SCIENCE_SEARCH -> articlesType = "Top Science Stories"
            MOST_POPULAR_SEARCH -> articlesType = "Most Popular Stories"
            CUSTOM_SEARCH_SEARCH -> articlesType = "Search Results"
            else -> articlesType = "Articles"
        }
    }

    fun initializeArticleArray() {
        list = loadArrayListFromSharedPreferences(articlePreferences, prefArticleKey)
        if (list.isEmpty() || list.size == 0) {
            startSearch()
        } else {
            articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
            recyclerView.adapter = articleAdapter
        }
    }

    override fun onPause() {
        saveArrayListToSharedPreferences(articlePreferences, prefArticleKey, list) //Save list to SharedPreferences
        super.onPause()
    }

    fun startSearch() {
        var connection: HttpURLConnection?
        //Check if the network is available. If it is, attempt the connection. If not, show a toast.
        if (networkIsAvailable(context!!)) {
            connection = connectToSite(stringToUrl(url)!!)
            if (connection != null) {
                fetchArticles(connection)
            }
        } else {
            swipeLayout.isRefreshing = false
            val toast = Toast.makeText(context!!, "Network Error", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    //This function gets the article in an async task managed by the Anko library.
    fun fetchArticles(connection: HttpURLConnection?) {
        doAsync {
            val result = readDataFromConnection(connection!!)
            uiThread {
                tempList = generateArticleArray(resultType, result)
                list = updateArrayList(list, tempList)
                articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
                swipeLayout.isRefreshing = false
                recyclerView.adapter = articleAdapter
            }
        }
    }
}