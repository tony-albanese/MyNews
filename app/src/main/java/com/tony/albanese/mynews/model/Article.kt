package com.tony.albanese.mynews.model

class Article(title: String, section: String, publishedDate: String, url: String, imageUrl: String, isRead: Boolean = false) {
    var mTitle = title
    var mSection = section
    var mPublishedDate = publishedDate
    var mUrl = url
    var mImageUrl = imageUrl
    var mIsRead = isRead
}