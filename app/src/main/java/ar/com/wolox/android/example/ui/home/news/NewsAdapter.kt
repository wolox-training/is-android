package ar.com.wolox.android.example.ui.home.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News

class NewsAdapter(val newsList: Array<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.newsTitle?.text = newsList[position].title
        holder?.newsText?.text = newsList[position].text
        holder?.newsCreatedAt?.text = newsList[position].createdAt
        holder?.newsLikeSelector?.setImageResource(R.drawable.news_like_selector)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.news_item_view, parent, false)
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
        // val newsImage = itemView.findViewById<ImageView>(R.id.vNewsImage)
    }
}

// only to Use Picasso
/*
* This is a method of class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
* fun updateWithUrl(url: String) {
            Picasso.get().load(url).into(newsImage)
        }
*
* This is how we create newsImage as Attribute of class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
* val newsImage = itemView.findViewById<ImageView>(R.id.vNewsImage)
*
* This is how to bind the ViewHolder and load the image from an URL. This goes inside override fun onBindViewHolder(holder: ViewHolder, position: Int)
* holder?.updateWithUrl(newsList[position].picture)
*
* */