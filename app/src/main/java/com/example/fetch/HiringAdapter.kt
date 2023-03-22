package com.example.fetch

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch.databinding.ItemHiringBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okio.Utf8.size
import java.util.*

class HiringAdapter(
    private val itemClicked:(Hiring) -> Unit
    ): RecyclerView.Adapter<HiringAdapter.HiringViewHolder>() {
        private var itemList: List<Hiring> = emptyList()

        @SuppressLint("NotifyDataSetChanged")
        fun bindData(items: List<Hiring>){
            itemList = items
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HiringViewHolder {
            val binding = ItemHiringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return HiringViewHolder(binding)
        }

        override fun onBindViewHolder(holder: HiringViewHolder, position: Int) {
            holder.bind(itemList[position])
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        inner class HiringViewHolder(
            val binding: ItemHiringBinding
        ): RecyclerView.ViewHolder(binding.root){
            fun bind(item: Hiring){
                binding.ListID.text = "List ID: ${item.listId}"
                binding.ItemName.text = "Name: ${item.name}"

                itemView.setOnClickListener {
                    itemClicked(item)
                }
            }
        }
}