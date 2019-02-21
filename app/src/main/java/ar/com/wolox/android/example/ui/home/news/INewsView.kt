package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.User
import ar.com.wolox.android.example.utils.UserSession

interface INewsView {
    fun loadNewsFailed()
    fun onLoginConnectionError()
    fun loadNewsSuccessfully(newsList: Array<News>, loggedUser: UserSession, it: Array<User>) {
    }
}