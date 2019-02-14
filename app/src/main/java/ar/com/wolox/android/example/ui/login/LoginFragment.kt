package ar.com.wolox.android.example.ui.login

import android.content.Intent
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.ui.signup.SignUpActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : WolmoFragment<LoginPresenter>(), ILoginView {

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        vLoginTermsAndConditions.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun setListeners() {

        vButtonSingUp.onClickListener {
            presenter.doSignUp()
        }

        vButtonLogIn.onClickListener {
            val emailLoginCondition = android.util.Patterns.EMAIL_ADDRESS.matcher(vLoginEmailInput.text).matches()
            if (vLoginEmailInput.text.isBlank()) {
                vLoginEmailInput.setError("Email is required")
            }
            if (vLoginPasswordInput.text.isBlank()) {
                vLoginPasswordInput.setError("Password is required")
            } else if (!emailLoginCondition) {
                vLoginEmailInput.setError("This is not a valid email")
            } else {
                loadingOn()
                presenter.doLogin(vLoginEmailInput.text.toString(), vLoginPasswordInput.text.toString())
            }
        }
    }

    override fun onUsernameSaved() {
        loadingOff()
        val intent = Intent(activity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    override fun goToSignup() {
        val intent = Intent(activity, SignUpActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginConnectionError() {
        loadingOff()
        Toast.makeText(context, getString(R.string.connection_error), Toast.LENGTH_SHORT).show()
    }

    override fun onLoginInvalidEmailError() {
        loadingOff()
        Toast.makeText(context, getString(R.string.invalid_email_error), Toast.LENGTH_SHORT).show()
    }

    private fun loadingOn() {
        vLoadingServerCall.visibility = View.VISIBLE
    }

    private fun loadingOff() {
        vLoadingServerCall.visibility = View.GONE
    }
}
