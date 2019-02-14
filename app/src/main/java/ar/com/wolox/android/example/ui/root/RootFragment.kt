package ar.com.wolox.android.example.ui.root

import ar.com.wolox.android.R

import ar.com.wolox.wolmo.core.fragment.WolmoFragment
class RootFragment : WolmoFragment<RootPresenter>(), IRootView {

    override fun layout(): Int = R.layout.fragment_root

    override fun init() {
        presenter.checkLoggedUser()
    }

}