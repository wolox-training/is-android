package ar.com.wolox.android.example.ui.root

import ar.com.wolox.android.example.ui.login.ILoginView
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class RootPresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<ILoginView>() {

    fun checkLoggedUser() {

    }

    fun getEmail(): String? {
        return mUserSession.email
    }

}
