package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.User
import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.android.example.network.UserService
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices

import javax.inject.Inject

class NewsPresenter @Inject constructor(private val mUserSession: UserSession, private val vRetrofitService: RetrofitServices) : BasePresenter<INewsView>() {

    private var usersInfo: Array<User> = emptyArray()

    fun loadNews(firstCallFlag: Boolean) {
        val service = vRetrofitService.getService(NewsService::class.java)
        val call = service.getAllNews()
        call.enqueue(
                networkCallback {
                    onResponseSuccessful {
                        if (it!!.isNotEmpty()) {
                            if (firstCallFlag)
                                loadUsers(it, mUserSession)
                            else
                                view.loadNewsSuccessfully(it, mUserSession, usersInfo)
                        } else
                            view.loadNewsFailed()
                    }
                    onCallFailure {
                        runIfViewAttached(
                                Runnable {
                                    view.onLoginConnectionError()
                                }
                        )
                    }
                }
        )
    }

    private fun loadUsers(news: Array<News>, mUserSession: UserSession) {
        val service = vRetrofitService.getService(UserService::class.java)
        val call = service.getAllUsers()
        call.enqueue(
                networkCallback {
                    onResponseSuccessful {
                        if (it!!.isNotEmpty()) {
                            usersInfo = it
                            view.loadNewsSuccessfully(news, mUserSession, usersInfo)
                        } else view.loadNewsFailed()
                    }
                    onCallFailure {
                        runIfViewAttached(
                                Runnable {
                                    view.onLoginConnectionError()
                                }
                        )
                    }
                }
        )
    }
}