package com.hughwu.btsetest.adapter

import android.os.Bundle
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
            itemBinding.tvSymbol.text = displayData.symbol
            itemBinding.tvPrice.text = displayData.price?.toBigDecimal()?.toString()
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<DisplayData>(){
        override fun areItemsTheSame(oldItem: DisplayData, newItem: DisplayData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DisplayData, newItem: DisplayData): Boolean {
            return oldItem.price == newItem.price && oldItem == newItem && oldItem.price!!.compareTo(newItem.price!!) == 0
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var displays: List<DisplayData>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    fun updateList(list: MutableList<DisplayData>) {
        val newList = ArrayList<DisplayData>(list)
        displays= newList
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