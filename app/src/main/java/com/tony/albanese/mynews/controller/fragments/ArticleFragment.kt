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
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.activities.WebViewActivity
import com.tony.albanese.mynews.controller.adapters.ArticleRecyclerAdapter
import com.tony.albanese.mynews.controller.utilities.ARTICLE_PREFERENCES
import com.tony.albanese.mynews.controller.utilities.URL_EXTRA_KEY
import com.tony.albanese.mynews.model.Article
import kotlinx.android.synthetic.main.fragment_base_layout.*
import java.util.*


/*
This class will serve as the basis for the other article fragments in order to reduce the repeated code.
 */

class ArticleFragment : Fragment() {
    lateinit var swipeLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var preferences: SharedPreferences
    lateinit var articleAdapter: ArticleRecyclerAdapter

    var list = ArrayList<Article>()
    var tempList = ArrayList<Article>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_layout, container, false)
        swipeLayout = view.findViewById(R.id.swipe_refresh_layout)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val subjectView = text_view_subject
        recyclerView = fragment_recycler_view
        preferences = activity!!.getSharedPreferences(ARTICLE_PREFERENCES, 0)
        articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
        recyclerView.adapter = articleAdapter
        recyclerView.layoutManager = layoutManager

        // topStoriesUrl = generateSearchUrl(context!!, TOP_STORIES_SEARCH)
        // subjectView.text = "Top Stories"
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

    fun newInsance(): ArticleFragment {

        return ArticleFragment()
    }
}