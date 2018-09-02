package com.tony.albanese.mynews.controller.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tony.albanese.mynews.R
import com.tony.albanese.mynews.model.Article
import kotlinx.android.synthetic.main.article_item_layout.view.*

class ArticleRecyclerAdapter(val list: ArrayList<Article>, context: Context, val clickListener: (View, Article) -> Unit) : RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleViewHolder>() {
    val mContext = context

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
            articleCategory.text = article.mSection
            articleDate.text = article.mPublishedDate
            with(mContext) {
                Picasso.get().load(article.mImageUrl).fit().error(resources.getDrawable(R.mipmap.default_image)).into(articleImage)
            }
            articleTitle.setOnClickListener { clickListener(itemView, article) }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}