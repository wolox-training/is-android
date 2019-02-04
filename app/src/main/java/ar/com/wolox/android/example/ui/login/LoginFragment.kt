package ar.com.wolox.android.example.ui.login

import android.content.Intent
import android.text.method.LinkMovementMethod
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.android.example.utils.onTextChanged
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : WolmoFragment<LoginPresenter>(), ILoginView {

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        vButtonLogIn.isEnabled = false
        vLoginTermsAndConditions.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun setListeners() {
        vLoginEmailInput.onTextChanged { vButtonLogIn.isEnabled = it.isNotBlank() }
        vButtonLogIn.onClickListener {
            presenter.storeUsername(vLoginEmailInput.text.toString())
        }
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, ViewPagerActivity::class.java)
        startActivity(intent)
    }
}
