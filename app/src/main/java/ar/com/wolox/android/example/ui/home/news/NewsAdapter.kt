package ar.com.wolox.android.example.ui.home.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat

class NewsAdapter(val newsList: Array<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prettytime = PrettyTime()
        val simpleDateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val simpleDateFormat = SimpleDateFormat(simpleDateFormatPattern)

        holder.newsTitle?.text = newsList[position].title
        holder.newsText?.text = newsList[position].text
        holder.newsCreatedAt?.text = prettytime.format(simpleDateFormat.parse(newsList[position].createdAt))
        holder.newsLikeSelector?.setImageResource(R.drawable.news_like_selector)
        holder.updateWithUrl(newsList[position].picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_item_view, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // val newsUserId = itemView.findViewById<TextView>(R.id.vNewsUserId)
        val newsTitle = itemView.findViewById<TextView>(R.id.vNewsTitle)
        val newsText = itemView.findViewById<TextView>(R.id.vNewsText)
        val newsCreatedAt = itemView.findViewById<TextView>(R.id.vNewsCreatedAt)
        val newsLikeSelector = itemView.findViewById<ImageView>(R.id.vNewsLikeSelector)
        val newsImage = itemView.findViewById<ImageView>(R.id.vNewsImage)

        fun updateWithUrl(url: String) {
            Glide.with(itemView).load(url.replace("http://", "https://")).into(newsImage)
        }
    }
}
