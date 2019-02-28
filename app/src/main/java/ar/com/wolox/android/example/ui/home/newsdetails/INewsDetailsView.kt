package ar.com.wolox.android.example.ui.home.newsdetails

import ar.com.wolox.android.example.model.News

interface INewsDetailsView {
    fun setNewsContent(currentNews: News)
    fun loadNewsFailed()
    fun onLoginConnectionError()
}