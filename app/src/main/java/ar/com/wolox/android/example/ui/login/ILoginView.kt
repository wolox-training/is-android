package ar.com.wolox.android.example.ui.login

interface ILoginView {

    fun onUsernameSaved()
    fun goToSignup()
    fun onLoginConnectionError()
    fun onLoginInvalidEmailError()
}
