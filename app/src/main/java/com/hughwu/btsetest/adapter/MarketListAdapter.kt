package com.hughwu.btsetest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hughwu.btsetest.databinding.MarketListAdapterBinding
import com.hughwu.btsetest.model.DisplayData

class MarketListAdapter:RecyclerView.Adapter<MarketListAdapter.MarketListViewHolder>() {
    inner class MarketListViewHolder(private val itemBinding: MarketListAdapterBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(displayData: DisplayData){
            itemBinding.itemData = displayData
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<DisplayData>(){
        override fun areItemsTheSame(oldItem: DisplayData, newItem: DisplayData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DisplayData, newItem: DisplayData): Boolean {
            return oldItem.price == newItem.price && oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    private var displays: List<DisplayData>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    fun updateList(list: MutableList<DisplayData>) {
        val newList = list.map { it.copy() }
        displays = newList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketListViewHolder {
        val itemBinding = MarketListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarketListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return displays.size
    }

    override fun onBindViewHolder(holder: MarketListViewHolder, position: Int) {
        holder.bind(displays[position])
    }
}