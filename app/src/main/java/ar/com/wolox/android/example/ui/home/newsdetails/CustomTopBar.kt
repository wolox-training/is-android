package ar.com.wolox.android.example.ui.home.newsdetails

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ar.com.wolox.android.R
import kotlinx.android.synthetic.main.custom_top_bar_back_button_and_title.view.*

class CustomTopBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_top_bar_back_button_and_title, this, true)
    }

    fun setText(text: String) {
        this.vCustomTopBarTitle.text = text
    }
}