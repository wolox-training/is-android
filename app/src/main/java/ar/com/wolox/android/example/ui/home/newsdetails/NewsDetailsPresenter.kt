package ar.com.wolox.android.example.ui.home.newsdetails

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.NewsService
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import javax.inject.Inject

class NewsDetailsPresenter @Inject constructor(private val mUserSession: UserSession, private val vRetrofitService: RetrofitServices) : BasePresenter<INewsDetailsView>() {
    fun getLoggedUser(): UserSession {
        return mUserSession
    }

    fun getSelectorState(currentNews: News) {
    }

    fun getPrettyTimeDate(createdAt: String): String {
        val prettytime = PrettyTime()
        val simpleDateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val simpleDateFormat = SimpleDateFormat(simpleDateFormatPattern)
        return prettytime.format(simpleDateFormat.parse(createdAt))
    }

    fun refreshNews(newsId: String) {
            val service = vRetrofitService.getService(NewsService::class.java)
            val call = service.getAllNews()
            call.enqueue(
                    networkCallback {
                        onResponseSuccessful {
                            if (it!!.isNotEmpty()) {
                                getNewsById(it, newsId)
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

    private fun getNewsById(newsList: Array<News>, newsId: String) {
        for (id in newsList.indices)
            if (newsList[id].id == newsId)
                view.setNewsContent(newsList[id])
    }
}