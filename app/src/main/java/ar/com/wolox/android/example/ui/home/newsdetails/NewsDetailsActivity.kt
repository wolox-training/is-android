package ar.com.wolox.android.example.ui.home.newsdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.wolmo.core.activity.WolmoActivity

class NewsDetailsActivity : WolmoActivity() {

    override fun layout(): Int = R.layout.activity_news_details

    override fun init() {
        replaceFragment(R.id.vNewsDetailsActivity, NewsDetailsFragment.newInstance(intent.getSerializableExtra(myKey) as News))
    }

    companion object {

        const val myKey = "key"

        fun start(context: Context, currentNews: News) {
            context.startActivity(Intent(context, NewsDetailsActivity::class.java).putExtra(myKey, currentNews))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = intent.getStringExtra(myKey)
    }
}
