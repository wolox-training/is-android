package ar.com.wolox.android.example.ui.home

import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.android.example.ui.login.LoginActivity
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor() : WolmoFragment<HomePresenter>(), IHomeView {

    @Inject
    lateinit var mNewsFragment: NewsFragment

    @Inject
    lateinit var mProfileFragment: ProfileFragment

    lateinit var viewAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int = R.layout.fragment_home

    override fun init() {
        viewAdapter = SimpleFragmentPagerAdapter(fragmentManager!!)
        viewAdapter.addFragment(mNewsFragment, "News")
        viewAdapter.addFragment(mProfileFragment, "Profile")

        vHomeViewPager.adapter = viewAdapter

        vTablayout.setupWithViewPager(vHomeViewPager)

        vTablayout.getTabAt(0)?.setIcon(R.drawable.news_button_selector)
        vTablayout.getTabAt(1)?.setIcon(R.drawable.profile_button_selector)
    }

    override fun setListeners() {
    }

    override fun returnToLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
