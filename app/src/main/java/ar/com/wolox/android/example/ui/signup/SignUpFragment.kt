package ar.com.wolox.android.example.ui.signup

import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.login.LoginActivity
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class SignUpFragment : WolmoFragment<SignUpPresenter>(), ISignUpView {

    override fun layout(): Int = R.layout.fragment_signup

    override fun init() {}

    override fun setListeners() {}

    override fun onBackPressed(): Boolean {
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        activity!!.finish()
        return super.onBackPressed()
    }
}
