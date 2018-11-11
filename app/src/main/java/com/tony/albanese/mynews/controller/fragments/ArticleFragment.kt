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
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.activities.WebViewActivity
import com.tony.albanese.mynews.controller.adapters.ArticleRecyclerAdapter
import com.tony.albanese.mynews.controller.utilities.*
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

    lateinit var url: String
    lateinit var articlesType: String

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

        subjectView.text = articlesType
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
        val searchType = bundle.getInt("search_type")
        val resultType = bundle.getInt("result_type")
        val prefKey = bundle.getString("pref_key")

        url = generateSearchUrl(context!!, searchType)
        preferences = activity!!.getSharedPreferences(prefKey, Context.MODE_PRIVATE)

        when (searchType) {
            TOP_STORIES_SEARCH -> articlesType = "Top Stories"
            TOP_SCIENCE_SEARCH -> articlesType = "Top Science Stories"
            MOST_POPULAR_SEARCH -> articlesType = "Most Popular Stories"
            CUSTOM_SEARCH_SEARCH -> articlesType = "Search Results"
            else -> articlesType = "Articles"
        }
    }
}