package com.tony.albanese.mynews.controller.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
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
import com.tony.albanese.mynews.controller.adapters.ArticleRecyclerAdapter
import com.tony.albanese.mynews.controller.utilities.*
import com.tony.albanese.mynews.model.Article
import kotlinx.android.synthetic.main.fragment_base_layout.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.HttpURLConnection

//This fragment displays the results of the user's custom search.

class CustomSearchFragment : Fragment() {
    var list = ArrayList<Article>()
    var tempList = ArrayList<Article>()
    lateinit var fragmentSearchUrl: String
    lateinit var activityCustomSearchUrl: String
    lateinit var articleAdapter: ArticleRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeLayout: SwipeRefreshLayout
    lateinit var articlePreferences: SharedPreferences
    lateinit var urlPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_layout, container, false)
        swipeLayout = view.findViewById(R.id.swipe_refresh_layout)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val subjectTextView = text_view_subject
        subjectTextView.text = getString(R.string.custom_search_title)

        recyclerView = fragment_recycler_view
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        articlePreferences = activity!!.getSharedPreferences(ARTICLE_PREFERENCES, 0)
        articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
        recyclerView.adapter = articleAdapter

        initializeArticleArray()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        urlPreferences = this.activity!!.getSharedPreferences(URL_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        activityCustomSearchUrl = getUrlFromSharedPreferences(ACTIVITY_CUSTOM_SEARCH_URL)
        fragmentSearchUrl = getUrlFromSharedPreferences(FRAGMENT_CUSTOM_SEARCH_URL)
        if (activityCustomSearchUrl != fragmentSearchUrl && activityCustomSearchUrl != "NONE") {
            urlPreferences.edit().putString(FRAGMENT_CUSTOM_SEARCH_URL, activityCustomSearchUrl).apply()
            startSearch()
        }
    }

    override fun onPause() {
        saveArrayListToSharedPreferences(articlePreferences, CUSTOM_SEARCH, list) //Save list to SharedPreferences
        super.onPause()
    }

    fun fetchArticles(connection: HttpURLConnection) {
        doAsync {
            val result = readDataFromConnection(connection!!)
            uiThread {
                list = generateArticleArray(CUSTOM_SEARCH_RESULTS, result)
                articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
                recyclerView.adapter = articleAdapter
            }
        }
    }

    fun onArticleClicked(view: View, article: Article) {
        view.setBackgroundColor(resources.getColor(R.color.colorIsRead))
        article.mIsRead = true
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.mUrl))
        startActivity(intent)
    }

    fun getUrlFromSharedPreferences(key: String): String {
        if (urlPreferences != null) {
            return urlPreferences.getString(key, "NONE")
        } else {
            return "NONE"
        }
    }

    fun startSearch() {
        var connection: HttpURLConnection?
        //Check if the network is available. If it is, attempt the connection. If not, show a toast.
        if (networkIsAvailable(context!!)) {
            connection = connectToSite(stringToUrl(activityCustomSearchUrl)!!)
            if (connection != null) {
                fetchArticles(connection)
            }
        } else {
            swipeLayout.isRefreshing = false
            val toast = Toast.makeText(context!!, "Network Error", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun initializeArticleArray() {
        list = loadArrayListFromSharedPreferences(articlePreferences, CUSTOM_SEARCH)
        if (list.isEmpty() || list.size == 0) {
            val toast = Toast.makeText(context, "No articles to display", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
            recyclerView.adapter = articleAdapter
        }
    }
}
