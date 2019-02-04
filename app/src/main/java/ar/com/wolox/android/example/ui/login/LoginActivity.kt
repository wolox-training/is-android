package ar.com.wolox.android.example.ui.login

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class LoginActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_login

    override fun init() {
        replaceFragment(R.id.vActivityLoginContent, LoginFragment())
    }
}
