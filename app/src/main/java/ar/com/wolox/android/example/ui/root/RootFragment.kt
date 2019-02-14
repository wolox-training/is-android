package ar.com.wolox.android.example.ui.root

import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.ui.login.LoginActivity

import ar.com.wolox.wolmo.core.fragment.WolmoFragment
class RootFragment : WolmoFragment<RootPresenter>(), IRootView {
    override fun layout(): Int = R.layout.fragment_root

    override fun init() {
        presenter.checkLoggedUser()
    }

    override fun onLoggedUser() {
        val intent = Intent(activity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun onNotLoggedUser() {
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}
