package ar.com.wolox.android.example.ui.signup

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter

import javax.inject.Inject

class SignUpPresenter @Inject constructor(private val mUserSession: UserSession) : BasePresenter<ISignUpView>()
