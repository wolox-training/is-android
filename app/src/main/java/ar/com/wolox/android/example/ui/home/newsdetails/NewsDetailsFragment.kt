package ar.com.wolox.android.example.ui.home.newsdetails

import android.os.Bundle
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news_details.*
import javax.inject.Inject

class NewsDetailsFragment @Inject constructor() : WolmoFragment<NewsDetailsPresenter>(), INewsDetailsView {

    override fun layout(): Int = R.layout.fragment_news_details

    override fun init() {
        val args = arguments
        val currentNews = args!!.getSerializable(myKey) as News
        vNewsDetailsExampleText.text = currentNews.text
    }

    companion object {
        private val myKey = "key"

        fun newInstance(currentNews: News): NewsDetailsFragment {
            val args = Bundle()
            args.putSerializable(myKey, currentNews)
            val fragment = NewsDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}