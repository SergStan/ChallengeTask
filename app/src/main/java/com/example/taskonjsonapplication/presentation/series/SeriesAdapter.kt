package com.example.taskonjsonapplication.presentation.series

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.taskonjsonapplication.databinding.RowItemSeriesBinding


class SeriesAdapter(
    private val layoutInflater: LayoutInflater,
    private val onItemClickListener: OnEpisodeItemClickListener
) : ListAdapter<SeriesRowState, SeriesAdapter.RowHolder>(RowStateDiffer) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        return RowHolder(RowItemSeriesBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(item.seasonNumber, item.episodeNumber)
        }
    }

    object RowStateDiffer : DiffUtil.ItemCallback<SeriesRowState>() {
        override fun areItemsTheSame(oldItem: SeriesRowState, newItem: SeriesRowState): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SeriesRowState, newItem: SeriesRowState): Boolean {
            return oldItem == newItem
        }
    }

    inner class RowHolder(private val binding: RowItemSeriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(rowState: SeriesRowState) {
            with(binding) {
                state = rowState
                executePendingBindings()
            }
        }
    }

    interface OnEpisodeItemClickListener {
        fun onItemClick(seasonNumber: String, episodeNumber: String)
    }
}

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    url?.let {
        Glide.with(context)
            .load(GlideUrl(it))
            .into(this)

    }
}