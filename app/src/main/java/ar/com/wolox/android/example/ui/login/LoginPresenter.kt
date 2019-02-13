package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.example.network.UserService
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.android.example.utils.networkCallback
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices

import javax.inject.Inject

class LoginPresenter @Inject constructor(private val mUserSession: UserSession, private val vRetrofitService: RetrofitServices) : BasePresenter<ILoginView>() {

    fun doLogin(email: String, pass: String) {
        val service = vRetrofitService.getService(UserService::class.java)
        val call = service.getUserByEmail(email)
        call.enqueue(
                networkCallback {
                    onResponseSuccessful {
                        if (it!!.isNotEmpty()) {
                            if (it[0].email == email && it[0].password == pass) {
                                mUserSession.email = email
                                view.onUsernameSaved()
                            }
                        } else view.onLoginInvalidEmailError()
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

    fun getEmail(): String? {
        return mUserSession.email
    }

    fun doSignUp() {
        view.goToSignup()
    }
}
