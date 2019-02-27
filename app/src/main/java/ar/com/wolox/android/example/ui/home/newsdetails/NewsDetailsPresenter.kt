package ar.com.wolox.android.example.ui.home.newsdetails

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import javax.inject.Inject

class NewsDetailsPresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<INewsDetailsView>() {
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
}