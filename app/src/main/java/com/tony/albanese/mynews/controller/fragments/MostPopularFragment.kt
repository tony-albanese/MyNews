package com.tony.albanese.mynews.controller.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.adapters.ArticleRecyclerAdapter
import com.tony.albanese.mynews.controller.utilities.*
import com.tony.albanese.mynews.model.Article
import kotlinx.android.synthetic.main.fragment_base_layout.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

//This fragment is responsible for displaying the most popular news articles.
class MostPopularFragment : Fragment() {

    var list = ArrayList<Article>()
    lateinit var mostPopularUrl: String
    lateinit var articleAdapter: ArticleRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeLayout: SwipeRefreshLayout

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
        mostPopularUrl = generateSearchUrl(context!!, 1)

        fetchArticles()

        swipeLayout.setOnRefreshListener {
            fetchArticles()
        }
        subjectView.text = "Most Read"
        recyclerView.layoutManager = layoutManager

    }

    fun fetchArticles() {
        swipeLayout.isRefreshing = true
        val connection = connectToSite(stringToUrl(mostPopularUrl)!!)
        doAsync {
            val result = readDataFromConnection(connection!!)
            uiThread {
                list = generateArticleArray(1, result)
                articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
                recyclerView.adapter = articleAdapter
                swipeLayout.isRefreshing = false
            }
        }
    }

    //This function is calle when the user clicks an article.
    fun onArticleClicked(view: View, article: Article) {
        view.setBackgroundColor(resources.getColor(R.color.colorAccent))
        article.mIsRead = true
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.mUrl))
        startActivity(intent)
    }
}
