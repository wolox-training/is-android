package ar.com.wolox.android.example.ui.home.newsdetails

import android.os.Bundle
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.onClickListener
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_news_details.*
import javax.inject.Inject

class NewsDetailsFragment @Inject constructor() : WolmoFragment<NewsDetailsPresenter>(), INewsDetailsView {

    override fun layout(): Int = R.layout.fragment_news_details

    override fun init() {
        val args = arguments
        val currentNews = args!!.getSerializable(KEY_NEWS) as News
        val username = args.getSerializable(KEY_USERS) as String
        configNews(currentNews, username)
    }

    private fun configNews(currentNews: News, username: String) {
        val loggedUser = presenter.getLoggedUser()
        val likeIconState = presenter.getSelectorState(currentNews)
        val prettyTimeDate = presenter.getPrettyTimeDate(currentNews.createdAt)
        vNewsDetailsUsername.text = username
        vNewsDetailsText.text = currentNews.text
        vNewsDetailsCreatedAt.text = prettyTimeDate
        vNewsDetailsLikeIcon.isSelected = currentNews.isLikedByUser(loggedUser.userId!!)
        getNewsImage(currentNews.picture)
    }

    private fun getNewsImage(picture: String) {
        Glide.with(this.view!!).load(picture.replace("http://", "https://")).into(vNewsDetailsImage)
    }

    override fun setListeners() {
        vNewsDetailsBackButton.onClickListener {
            // vNewsDetailsBackButton.setColorFilter(ContextCompat.getColor(this.context!!, R.color.primary_dark_material_dark), android.graphics.PorterDuff.Mode.MULTIPLY)
            activity!!.onBackPressed()
        }
        vNewsDetailsLikeIcon.onClickListener {
            vNewsDetailsLikeIcon.isSelected = !vNewsDetailsLikeIcon.isSelected
        }
    }

    companion object {
        private val KEY_NEWS = "newsKey"
        private val KEY_USERS = "usersKey"

        fun newInstance(currentNews: News, user: String): NewsDetailsFragment {
            val args = Bundle()
            args.putSerializable(KEY_NEWS, currentNews)
            args.putSerializable(KEY_USERS, user)
            val fragment = NewsDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}