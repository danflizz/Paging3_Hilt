package com.example.encoratask.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.encoratask.R
import com.example.encoratask.data.model.DetailsResponse
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: DetailsResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { it ->
            if (it.containsKey(ARG_ITEM)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                item = it.getSerializable(ARG_ITEM) as DetailsResponse
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        val toolbar = activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        toolbar?.title = item?.name

        activity?.findViewById<ImageView>(R.id.img_toolbar)?.let { image ->
            Glide.with(this).load(item?.image).into(image)
        }

        item?.let {
            val type =
                if (it.type?.isEmpty() == true) getString(R.string.unknown) else item?.type
            rootView.findViewById<TextView>(R.id.item_detail).text = HtmlCompat.fromHtml(
                getString(
                    R.string.details, it.status,
                    it.species, type, it.gender
                ), HtmlCompat.FROM_HTML_MODE_COMPACT
            )
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
        const val ARG_ITEM = "item"
    }
}