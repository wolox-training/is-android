package ar.com.wolox.android.example.ui.home

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.login.LoginActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : WolmoFragment<HomePresenter>(), IHomeView {

    override fun layout(): Int = R.layout.fragment_home

    override fun init() {
    }

    override fun setListeners() {
        vHomeLogOutButton.onClickListener {
            presenter.doLogout()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun returnToLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        intent.removeFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.removeFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        // activity!!.finish()
    }
}
