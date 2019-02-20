package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices

import javax.inject.Inject

class NewsPresenter @Inject constructor(private val mUserSession: UserSession, private val vRetrofitService: RetrofitServices) : BasePresenter<INewsView>() {
    fun loadNews() {
        val service = vRetrofitService.getService(NewsService::class.java)
        val call = service.getAllNews()
        call.enqueue(
                networkCallback {
                    onResponseSuccessful {
                        if (it!!.isNotEmpty())
                            view.loadNewsSuccessfully(it, mUserSession)
                        else view.loadNewsFailed()
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