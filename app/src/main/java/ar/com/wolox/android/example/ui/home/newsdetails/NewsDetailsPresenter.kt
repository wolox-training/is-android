package ar.com.wolox.android.example.ui.home.newsdetails

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsDetailsPresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<INewsDetailsView>()