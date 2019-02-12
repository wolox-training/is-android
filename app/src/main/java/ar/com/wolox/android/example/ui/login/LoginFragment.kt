package ar.com.wolox.android.example.ui.login

import android.content.Intent
import android.text.Editable
import android.text.method.LinkMovementMethod
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

        if (presenter.getEmail()?.isNotEmpty()!!) {
            val intent = Intent(activity, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            // activity!!.finish()
        }
    }

    override fun setListeners() {

        vButtonSingUp.onClickListener {
            presenter.doSignUp()
        }

        vButtonLogIn.onClickListener {
            if (checkValidField(vLoginEmailInput.text, vLoginPasswordInput.text))
                presenter.doLogin(vLoginEmailInput.text.toString())
        }
    }

    private fun checkValidField(email: Editable, pass: Editable): Boolean {
        val emailLoginCondition = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return if (email.isBlank()) {
            vLoginEmailInput.error = getString(R.string.required_email_error)
            false
        } else if (pass.isBlank()) {
            vLoginPasswordInput.error = getString(R.string.required_password_error)
            false
        } else if (!emailLoginCondition) {
            vLoginEmailInput.error = getString(R.string.invalid_email_error)
            false
        } else true
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
        // activity!!.finish()
    }

    override fun goToSignup() {
        val intent = Intent(activity, SignUpActivity::class.java)
        startActivity(intent)
    }
}
