package com.tony.albanese.mynews.controller.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.controller.adapters.ArticleRecyclerAdapter
import com.tony.albanese.mynews.controller.utilities.generateSearchUrl
import com.tony.albanese.mynews.model.Article
import kotlinx.android.synthetic.main.fragment_base_layout.*
import java.util.*







//This fragment displays the stop stories for science.
class TopScienceStoriesFragment : Fragment() {
    var list = ArrayList<Article>()
    lateinit var scienceUrl: String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView = fragment_recycler_view
        val layoutManager = LinearLayoutManager(context)
        val adapter = ArticleRecyclerAdapter(list)
        val dummyView = text_view_dummy
        scienceUrl = generateSearchUrl(context!!, 3)


        dummyView.text = "Top Science Stories"
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    fun generateTestList(): ArrayList<Article> {
        list.add(Article("Title", "Section", "Date", "www.dd.dd", "www.dee.dd"))
        return list
    }

}

