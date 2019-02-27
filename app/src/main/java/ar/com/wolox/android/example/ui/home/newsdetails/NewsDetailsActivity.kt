package ar.com.wolox.android.example.ui.home.newsdetails

import android.content.Context
import android.content.Intent
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class NewsDetailsActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_news_details

    override fun init() {
        replaceFragment(R.id.vNewsDetailsActivity, NewsDetailsFragment.newInstance(intent.getSerializableExtra(KEY_NEWS) as News, intent.getSerializableExtra(KEY_USERS) as String))
    }

    companion object {

        private val KEY_NEWS = "newsKey"
        private val KEY_USERS = "usersKey"

        fun start(context: Context, currentNews: News, user: String) {
            context.startActivity(Intent(context, NewsDetailsActivity::class.java).putExtra(KEY_NEWS, currentNews).putExtra(KEY_USERS, user))
        }
    }
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getStringExtra(myKey)
    }*/
}
