package ar.com.wolox.android.example.ui.home.news

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.User
import ar.com.wolox.android.example.ui.home.newsdetails.NewsDetailsActivity
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.news_item_view.*
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<NewsPresenter>(), INewsView {

    companion object {
        private var firstCallFlag = true
        private var loadingFlag = false
        private var itemsToReload = 5
        private const val initNewsToLoad = 5 // This number is going to be multiplied by the number of news that the API return at the GET method (in this case: 3)
        private const val newsPerReload = 3 // This number is going to be multiplied by the number of news that the API return at the GET method (in this case: 3)
    }

    private lateinit var newsList: Array<News>

    private lateinit var rv: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var manager: LinearLayoutManager

    override fun layout(): Int = R.layout.fragment_news

    override fun init() {
        loadingOn()
        rv = vRecyclerViewNews
        manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        resetNewsList()
        presenter.loadNews(firstCallFlag)
    }

    override fun loadNewsSuccessfully(newsListToAdd: Array<News>, loggedUser: UserSession, users: Array<User>) {
        loadingOff()
        if (firstCallFlag) { // To populate newsList and first config
            fillNewsList(newsListToAdd, initNewsToLoad)
            initConfigAfterNewsLoad(loggedUser, users)
        } else {
            fillNewsList(newsListToAdd, newsPerReload)
            adapter.setNews(newsList)
            adapter.notifyDataSetChanged()
        }
        Toast.makeText(context, getString(R.string.news_load_success), Toast.LENGTH_SHORT).show()
    }

    override fun setListeners() {

        vRecyclerViewNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!firstCallFlag) {
                    val lastVisibleItem = manager.findLastCompletelyVisibleItemPosition()
                    if (!loadingFlag) {
                        if (lastVisibleItem >= adapter.itemCount - itemsToReload) {
                            loadingOn()
                            presenter.loadNews(firstCallFlag)
                        }
                    }
                }
            }
        })
        vNewsSwipeRefresh.setOnRefreshListener {
            vNewsSwipeRefresh.isRefreshing = false
            reset()
        }
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
        loadingFlag = true
        vLoadingNews.visibility = View.VISIBLE
    }

    private fun loadingOff() {
        loadingFlag = false
        vLoadingNews.visibility = View.GONE
    }

    private fun initConfigAfterNewsLoad(loggedUser: UserSession, users: Array<User>) {
        rv.layoutManager = manager
        adapter = NewsAdapter(newsList, loggedUser, users) {
            onShowDetails(it, vNewsUser.text as String)
        }
        rv.adapter = adapter
        adapter.notifyDataSetChanged()
        firstCallFlag = false
    }

    private fun onShowDetails(currentNews: News, user: String) {
        NewsDetailsActivity.start(requireContext(), currentNews, user)
    }

    private fun fillNewsList(newsListToAdd: Array<News>, timesToLoad: Int) {
        var counter = timesToLoad
        do {
            addNews(newsListToAdd)
            counter--
        } while (counter > 0)
    }

    private fun addNews(newsListToAdd: Array<News>) {
        newsList = newsList.plus(newsListToAdd)
    }

    private fun reset() {
        loadingOn()
        resetNewsList()
        firstCallFlag = true
        presenter.loadNews(firstCallFlag)
    }

    private fun resetNewsList() {
        newsList = emptyArray()
    }
}
