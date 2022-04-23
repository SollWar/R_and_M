package com.example.sollwar.randm.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sollwar.randm.data.model.Result
import com.example.sollwar.randm.databinding.ItemCharacterListBinding
import com.squareup.picasso.Picasso

class CharactersAdapter : PagingDataAdapter<Result, CharactersAdapter.Holder>(MovieDiffCallback()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val result = getItem(position) ?: return
        with(holder.binding) {
            name.text = result.name
            species.text = result.species
            gender.text = result.gender
            Picasso.get()
                .load(result.image)
                .into(avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterListBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    class Holder(
        val binding: ItemCharacterListBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

class MovieDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}