package ar.com.wolox.android.example.ui.home.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.model.User
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.android.example.utils.onClickListener
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat

class NewsAdapter(val newsList: Array<News>, val loggedUser: UserSession, val users: Array<User>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prettytime = PrettyTime()
        val simpleDateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val simpleDateFormat = SimpleDateFormat(simpleDateFormatPattern)

        // holder.newsTitle?.text = newsList[position].title
        holder.newsUsername.text = getUsername(newsList, users, position)
        holder.newsText.text = newsList[position].text
        holder.newsCreatedAt?.text = prettytime.format(simpleDateFormat.parse(newsList[position].createdAt))
        holder.newsLikeSelector?.setImageResource(R.drawable.news_like_selector)
        holder.updateWithUrl(newsList[position].picture)

        if (newsList[position].likes.contains(loggedUser.userId)) {
            holder.newsLikeSelector.isSelected = true
        }
        holder.newsLikeSelector.onClickListener {
            holder.newsLikeSelector.isSelected = !holder.newsLikeSelector.isSelected
        }
    }

    private fun getUsername(newsList: Array<News>, users: Array<User>, position: Int): String {
        var title = newsList[position].title
        for (user in users.indices) {
            if (users[user].id == newsList[position].userId)
                title = users[user].name
        }
        return title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsUsername = itemView.findViewById<TextView>(R.id.vNewsUser)
        // val newsTitle = itemView.findViewById<TextView>(R.id.vNewsTitle)
        val newsText = itemView.findViewById<TextView>(R.id.vNewsText)
        val newsCreatedAt = itemView.findViewById<TextView>(R.id.vNewsCreatedAt)
        val newsLikeSelector = itemView.findViewById<ImageView>(R.id.vNewsLikeSelector)
        val newsImage = itemView.findViewById<ImageView>(R.id.vNewsImage)

        fun updateWithUrl(url: String) {
            Glide.with(itemView).load(url.replace("http://", "https://")).into(newsImage)
        }
    }
}