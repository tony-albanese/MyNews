package com.tony.albanese.mynews.controller.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.model.Article
import kotlinx.android.synthetic.main.article_item_layout.view.*

class ArticleRecyclerAdapter(val list: ArrayList<Article>): RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleViewHolder>()   {

    class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //Get references to the views that will be populated by the adapter.
        var articleImage = itemView.article_image_view
        var articleTitle = itemView.text_view_article_title
        var articleCategory = itemView.text_view_article_category
        var articleDate = itemView.text_view_article_date

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ArticleViewHolder {
        //This function will inflate the layout used to populate the recycler views.
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.article_item_layout, viewGroup, false)
        return ArticleViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ArticleViewHolder, i: Int) {
        //This where the date from the ArrayList gets bound to the individual views.
        var article = list[i]
        with(viewHolder){
            articleTitle.text = article.mTitle
            articleCategory.text = article.mTitle
            articleDate.text = article.mPublishedDate
            Picasso.get().load(article.mImageUrl).fit().into(articleImage)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}