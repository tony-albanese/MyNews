package com.tony.albanese.mynews.controller.fragments

import android.app.AlertDialog
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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.HttpURLConnection

//This fragment displays the results of the user's custom search.

class CustomSearchFragment : Fragment() {
    var list = ArrayList<Article>()
    var newArticleList = ArrayList<Article>()
    lateinit var fragmentSearchUrl: String
    lateinit var activityCustomSearchUrl: String
    lateinit var articleAdapter: ArticleRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeLayout: SwipeRefreshLayout
    lateinit var urlPreferences: SharedPreferences
    lateinit var articlePreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_base_layout, container, false)
        swipeLayout = view.findViewById(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        swipeLayout.isEnabled = false
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val subjectTextView = text_view_subject
        val layoutManager = LinearLayoutManager(context)
        subjectTextView.text = getString(R.string.custom_search_title)

        urlPreferences = this.activity!!.getSharedPreferences(URL_SHARED_PREFERENCES, Context.MODE_PRIVATE)
        articlePreferences = this.activity!!.getSharedPreferences(ARTICLE_PREFERENCES, Context.MODE_PRIVATE)
        recyclerView = fragment_recycler_view
        recyclerView.layoutManager = layoutManager
        articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
        recyclerView.adapter = articleAdapter
    }

    override fun onResume() {
        initializeArticleArray()
        super.onResume()
    }

    override fun onPause() {
        saveArrayListToSharedPreferences(articlePreferences, CUSTOM_SEARCH_KEY, list) //Save list to SharedPreferences
        super.onPause()
    }
    
    fun fetchArticles(connection: HttpURLConnection) {
        doAsync {
            val result = readDataFromConnection(connection)
            uiThread {
                list = generateArticleArray(CUSTOM_SEARCH_RESULTS, result)
                if (list.isEmpty()) createNoArticleAlertDialog()
                articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
                recyclerView.adapter = articleAdapter
                swipeLayout.isRefreshing = false
                setVisibilityEmptyView()
            }
        }
    }

    fun onArticleClicked(view: View, article: Article) {
        view.setBackgroundColor(resources.getColor(R.color.colorIsRead))
        article.mIsRead = true
        val intent = Intent(context, WebViewActivity::class.java).apply {
            putExtra(URL_EXTRA_KEY, article.mUrl)
        }
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
        val connection: HttpURLConnection?
        //Check if the network is available. If it is, attempt the connection. If not, show a toast.
        if (networkIsAvailable(context!!) && activityCustomSearchUrl != "NONE") {
            connection = connectToSite(stringToUrl(activityCustomSearchUrl)!!)
            if (connection != null) {
                fetchArticles(connection)
            }
        } else {
            swipeLayout.isRefreshing = false
        }

    }

    fun initializeArticleArray() {
        activityCustomSearchUrl = getUrlFromSharedPreferences(ACTIVITY_CUSTOM_SEARCH_URL)
        fragmentSearchUrl = getUrlFromSharedPreferences(FRAGMENT_CUSTOM_SEARCH_URL)
        list = loadArrayListFromSharedPreferences(articlePreferences, CUSTOM_SEARCH_KEY)
        newArticleList = loadArrayListFromSharedPreferences(articlePreferences, NEW_ARTICLE_KEY)
        val tempList = updateArrayList(list, newArticleList)
        list = tempList

        if (!fragmentSearchUrl.equals(activityCustomSearchUrl)) {
            urlPreferences.edit().putString(FRAGMENT_CUSTOM_SEARCH_URL, activityCustomSearchUrl).apply()
            startSearch()
        } else {
            articleAdapter = ArticleRecyclerAdapter(list, context!!, { view: View, article: Article -> onArticleClicked(view, article) })
            newArticleList.clear()
            saveArrayListToSharedPreferences(articlePreferences, NEW_ARTICLE_KEY, newArticleList)
            recyclerView.adapter = articleAdapter
        }
        setVisibilityEmptyView()
    }

    fun createNoArticleAlertDialog() {
        val alertDialog = AlertDialog.Builder(activity).create()
        with(alertDialog) {
            setTitle(activity?.getString(R.string.dialog_title))
            setMessage(activity?.getString(R.string.dialog_message))
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, which ->
                alertDialog.dismiss()
            }
        }
        alertDialog.show()
    }

    //Function to check for empty list and set the visibility of the empty view.
    fun setVisibilityEmptyView() {
        if (list.isEmpty()) {
            tv_fragment_empty_view.visibility = View.VISIBLE
            fragment_recycler_view.visibility = View.GONE
        } else {
            tv_fragment_empty_view.visibility = View.GONE
            fragment_recycler_view.visibility = View.VISIBLE
        }
    }
}
