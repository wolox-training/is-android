package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter

import javax.inject.Inject

class LoginPresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<ILoginView>() {

    fun doLogin(email: String) {
        mUserSession.email = email
        view.onUsernameSaved()
    }

    fun getEmail(): String? {
        return mUserSession.email
    }

    fun doSignUp() {
        view.goToSignup()
    }
}
