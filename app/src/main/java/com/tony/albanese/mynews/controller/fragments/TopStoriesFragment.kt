package com.tony.albanese.mynews.controller.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.adapters.ArticleRecyclerAdapter
import com.tony.albanese.mynews.controller.utilities.*
import com.tony.albanese.mynews.model.Article
import kotlinx.android.synthetic.main.fragment_base_layout.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


//This fragment is responsible for displaying the top stories news articles.
class TopStoriesFragment : Fragment() {
    var list = ArrayList<Article>()
    var tempList = ArrayList<Article>()
    lateinit var mostPopularUrl: String
    lateinit var articleAdapter: ArticleRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var swipeLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_layout, container, false)
        swipeLayout = view.findViewById(R.id.swipe_refresh_layout)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView = fragment_recycler_view
        val layoutManager = LinearLayoutManager(context)
        val subjectView = text_view_subject
        mostPopularUrl = generateSearchUrl(context!!, 2)
        subjectView.text = "Top Stories"
        recyclerView.layoutManager = layoutManager

        swipeLayout.setOnRefreshListener {
            fetchArticles()
        }

        fetchArticles()
    }

    fun fetchArticles() {
        val connection = connectToSite(stringToUrl(mostPopularUrl)!!)
        swipeLayout.isRefreshing = true
        doAsync {
            val result = readDataFromConnection(connection!!)
            uiThread {
                tempList = generateArticleArray(2, result)
                list = tempList
                articleAdapter = ArticleRecyclerAdapter(list, context!!)
                swipeLayout.isRefreshing = false
                recyclerView.adapter = articleAdapter
            }
        }
    }
}


