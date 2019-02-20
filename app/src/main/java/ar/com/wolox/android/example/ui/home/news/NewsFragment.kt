package ar.com.wolox.android.example.ui.home.news

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView {
    override fun layout(): Int = R.layout.fragment_news

    override fun init() {
        loadingOn()
        presenter.loadNews()
    }

    override fun loadNewsSuccessfully(newsList: Array<News>) {
        loadingOff()
        val rv = vRecyclerViewNews
        rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter = NewsAdapter(newsList)
        rv.adapter = adapter
        Toast.makeText(context, getString(R.string.news_load_success), Toast.LENGTH_SHORT).show()
    }

    override fun loadNewsFailed() {
        loadingOff()
        Toast.makeText(context, getString(R.string.news_load_failed), Toast.LENGTH_SHORT).show()
    }

    override fun onLoginConnectionError() {
        loadingOff()
        Toast.makeText(context, getString(R.string.connection_error), Toast.LENGTH_SHORT).show()
    }

    private fun loadingOn() {
        vLoadingNews.visibility = View.VISIBLE
    }

    private fun loadingOff() {
        vLoadingNews.visibility = View.GONE
    }
}
