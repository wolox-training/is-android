package ar.com.wolox.android.example.ui.home

import android.content.Intent
import android.os.Build
import androidx.viewpager.widget.ViewPager
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.android.example.ui.login.LoginActivity
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment @Inject constructor() : WolmoFragment<HomePresenter>(), IHomeView {

    companion object {
        val homeTitleTextDefault = "Home: "
    }

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
        if (Build.VERSION.SDK_INT > 21)
            activity!!.window.statusBarColor = resources.getColor(R.color.colorPrimary)
        vHomeViewPager.adapter = viewAdapter

        vTablayout.setupWithViewPager(vHomeViewPager)

        vTablayout.getTabAt(0)?.setIcon(R.drawable.news_button_selector)
        vTablayout.getTabAt(1)?.setIcon(R.drawable.profile_button_selector)

        vHomeTitleText.text = homeTitleTextDefault + vTablayout.getTabAt(0)!!.text
    }

    override fun setListeners() {
        vHomeViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                vHomeTitleText.text = homeTitleTextDefault + vTablayout.getTabAt(position)!!.text
                if (position == 1)
                    vHomeFabAdd.hide()
                else
                    vHomeFabAdd.show()
            }
        }
        )

        vHomeFabAdd.onClickListener {
            presenter.addNews()
        }
    }

    override fun goToCreateNews() {
        // TODO: createNews layout, fragment, presenter, view
    }

    override fun returnToLogin() {
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
