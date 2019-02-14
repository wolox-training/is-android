package ar.com.wolox.android.example.ui.root

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class RootActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_root

    override fun init() {
        replaceFragment(R.id.vActivityRootContent, RootFragment())
    }
}