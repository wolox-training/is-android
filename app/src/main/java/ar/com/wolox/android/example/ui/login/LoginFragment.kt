package ar.com.wolox.android.example.ui.login

import android.content.Intent
import android.text.method.LinkMovementMethod
import android.widget.Toast
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : WolmoFragment<LoginPresenter>(), ILoginView {

    override fun layout(): Int = R.layout.fragment_login

    override fun init() {
        vLoginTermsAndConditions.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun setListeners() {

        vButtonLogIn.onClickListener {
            val emailLoginCondition = android.util.Patterns.EMAIL_ADDRESS.matcher(vLoginEmailInput.text).matches()
            if (vLoginEmailInput.text.isBlank() || vLoginPasswordInput.text.isBlank()) {
                Toast.makeText(context, resources.getString(R.string.required_email_and_password_error), Toast.LENGTH_SHORT).show()
            } else if (!emailLoginCondition && vLoginPasswordInput.text.isNotBlank()) {
                Toast.makeText(context, resources.getString(R.string.invalid_email_error), Toast.LENGTH_SHORT).show()
            } else {
                presenter.storeUsername(vLoginEmailInput.text.toString())
            }
        }
    }

    override fun onUsernameSaved() {
        val intent = Intent(activity, ViewPagerActivity::class.java)
        startActivity(intent)
    }
}
