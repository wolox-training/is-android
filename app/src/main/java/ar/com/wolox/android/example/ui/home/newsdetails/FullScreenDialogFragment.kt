package ar.com.wolox.android.example.ui.home.newsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ar.com.wolox.android.R
import ar.com.wolox.android.example.utils.onClickListener
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_top_bar_back_button_and_title.view.*
import kotlinx.android.synthetic.main.fragment_news_details_image_fullscreen.*
import kotlinx.android.synthetic.main.fragment_news_details_image_fullscreen.view.*

class FullScreenDialogFragment : DialogFragment() {

    companion object {
        public const val Tag = "FullScreenDialogFragment"

        private val KEY_IMAGE = "image"

        fun newInstance(image: String): FullScreenDialogFragment {
            val args = Bundle()
            args.putSerializable(KEY_IMAGE, image)
            val fragment = FullScreenDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_news_details_image_fullscreen, container, false)
        view.vNewsDetailsImageFullscreenTopBar.vCustomTopBarTitle.text = "Close"
        val exitButton = view.vNewsDetailsImageFullscreenTopBar.vCustomTopBarBackButton
        exitButton.setImageResource(R.drawable.ic_exit)
        exitButton.onClickListener { dialog!!.dismiss() }
        return view
    }

    override fun onStart() {
        super.onStart()
        val args = arguments
        val fullscreenImage = args!!.getSerializable(KEY_IMAGE) as String
        setImage(fullscreenImage)
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window.setLayout(width, height)
        }
    }

    private fun setImage(fullscreenImage: String) {
        Glide.with(this.view!!).load(fullscreenImage.replace("http://", "https://")).into(vNewsDetailsImageFullscreen)
    }
}