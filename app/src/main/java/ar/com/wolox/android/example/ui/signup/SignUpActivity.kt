package ar.com.wolox.android.example.ui.signup

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class SignUpActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_signup

    override fun init() {
        replaceFragment(R.id.vActivitySignUpContent, SignUpFragment())
    }
}
