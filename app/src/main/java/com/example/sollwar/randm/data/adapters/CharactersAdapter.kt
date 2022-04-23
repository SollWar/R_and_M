package com.example.sollwar.randm.data.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sollwar.randm.R
import com.example.sollwar.randm.data.model.Result
import com.example.sollwar.randm.databinding.ItemCharacterListBinding
import com.squareup.picasso.Picasso



class CharactersAdapter(
    private val onCharacterListener: OnCharacterListener,
    private val context: Context
) : PagingDataAdapter<Result, CharactersAdapter.Holder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val result = getItem(position) ?: return
        holder.bind(result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterListBinding.inflate(inflater, parent, false)
        return Holder(binding, onCharacterListener, context)
    }

    class Holder(
        private val binding: ItemCharacterListBinding,
        private val onCharacterListener: OnCharacterListener,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var result: Result

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(result: Result) {
            this.result = result
            binding.name.text = this.result.name
            binding.species.text = context.getString(R.string.species, this.result.species)
            binding.gender.text = context.getString(R.string.gender, this.result.gender)
            Picasso.get()
                .load(this.result.image)
                .into(binding.avatar)
        }

        override fun onClick(p0: View?) {
            onCharacterListener.onCharacterClick(result)
        }

    }

    interface OnCharacterListener {
        fun onCharacterClick(result: Result)
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}