package com.tony.albanese.mynews.controller.fragments

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

//This fragment is responsible for displaying the most popular news articles.
class MostPopularFragment : Fragment() {
    var list = ArrayList<Article>()
    var tempList = ArrayList<Article>()
    lateinit var mostPopularUrl: String
    lateinit var articleAdapter: ArticleRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeLayout: SwipeRefreshLayout
    lateinit var preferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_layout, container, false)
        swipeLayout = view.findViewById(R.id.swipe_refresh_layout)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = fragment_recycler_view
        val layoutManager = LinearLayoutManager(context)
        val subjectView = text_view_subject
        subjectView.text = "Most Read"
        mostPopularUrl = generateSearchUrl(context!!, MOST_POPULAR_SEARCH)
        recyclerView.layoutManager = layoutManager
        preferences = activity!!.getSharedPreferences(ARTICLE_PREFERENCES, 0)
        articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
        recyclerView.adapter = articleAdapter
        initializeArticleArray()
        swipeLayout.setOnRefreshListener {
            startSearch()
        }
    }

    override fun onPause() {
        saveArrayListToSharedPreferences(preferences, MOST_POPULAR, list) //Save list to SharedPreferences
        super.onPause()
    }

    fun fetchArticles(connection: HttpURLConnection) {
        doAsync {
            val result = readDataFromConnection(connection!!)
            uiThread {
                tempList = generateArticleArray(MOST_POPULAR_SEARCH, result)
                list = updateArrayList(list, tempList)
                articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
                recyclerView.adapter = articleAdapter
                swipeLayout.isRefreshing = false
            }
        }
    }

    //This function is calle when the user clicks an article.
    fun onArticleClicked(view: View, article: Article) {
        view.setBackgroundColor(resources.getColor(R.color.colorIsRead))
        article.mIsRead = true
        val intent = Intent(context, WebViewActivity::class.java).apply {
            putExtra(URL_EXTRA, article.mUrl)
        }
        startActivity(intent)
    }

    /*
Initialize the article array by checking loading it from shared preferences. If
the ArrayList is empty or has no elements, then initialize the search. Otherwise,
the empty list is passed to the adapter.
 */

    fun initializeArticleArray() {
        list = loadArrayListFromSharedPreferences(preferences, MOST_POPULAR)
        if (list.isEmpty() || list.size == 0) {
            startSearch()
        } else {
            articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
            recyclerView.adapter = articleAdapter
        }
    }

    fun startSearch() {
        var connection: HttpURLConnection?
        //Check if the network is available. If it is, attempt the connection. If not, show a toast.
        if (networkIsAvailable(context!!)) {
            connection = connectToSite(stringToUrl(mostPopularUrl)!!)
            if (connection != null) {
                fetchArticles(connection)
            }
        } else {
            swipeLayout.isRefreshing = false
            val toast = Toast.makeText(context!!, "Network Error", Toast.LENGTH_SHORT)
            toast.show()

        }
    }
}
