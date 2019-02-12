package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class HomeActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_home

    override fun init() {
        replaceFragment(R.id.vActivityHomeContent, HomeFragment())
    }
}
