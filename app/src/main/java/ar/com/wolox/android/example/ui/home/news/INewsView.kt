package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News

interface INewsView {
    fun loadNewsFailed()
    fun onLoginConnectionError()
    fun loadNewsSuccessfully(newsList: Array<News>) {
    }
}