package com.example.encoratask.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.encoratask.R
import com.example.encoratask.data.repository.CharacterPaging
import com.example.encoratask.presentation.view.ItemDetailActivity
import com.example.encoratask.presentation.view.ItemDetailFragment

class CharacterAdapter :
    PagingDataAdapter<CharacterPaging, CharacterAdapter.CharacterViewHolder>(CHARACTER_COMPARATOR) {
    private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
        val item = v.tag as CharacterPaging
        val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
            putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
        }
        v.context.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = getItem(position)
        item?.let {
            with(context) {
                holder.idView.text = getString(R.string.id_name, it.id, it.name)
                holder.contentView.text = getString(R.string.status, it.status)
            }

            with(holder.itemView) {
                tag = it
                setOnClickListener(onClickListener)
            }
        }

    }

    class CharacterViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.id_text)
        val contentView: TextView = view.findViewById(R.id.content)
    }

    companion object {
        private val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<CharacterPaging>() {
            override fun areItemsTheSame(
                oldItem: CharacterPaging,
                newItem: CharacterPaging
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterPaging,
                newItem: CharacterPaging
            ): Boolean =
                oldItem == newItem

        }
    }
}