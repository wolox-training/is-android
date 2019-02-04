package ar.com.wolox.android.example.ui.login

import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.android.example.utils.onTextChanged
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : WolmoFragment<LoginPresenter>(), ILoginView {

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        buttonLogIn.isEnabled = false
    }

    override fun setListeners() {
        loginEmailInput.onTextChanged { buttonLogIn.isEnabled = it.isNotBlank() }
        buttonLogIn.onClickListener {
            presenter.storeUsername(loginEmailInput.text.toString())
        }
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, ViewPagerActivity::class.java)
        startActivity(intent)
    }
}
