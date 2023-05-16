package com.example.bluetooth_def

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bluetooth_def.databinding.ListItemBinding

class ItemAdapter : ListAdapter<ListItem, ItemAdapter.MyHolder>(Comparator()){
    class MyHolder(view: View) : RecyclerView.ViewHolder(view){
        private var binding = ListItemBinding.bind(view)
        fun bind(item: ListItem) = with(binding){
            name.text = item.nameDevises
            mac.text = item.macAddress
        }
    }

    class Comparator : DiffUtil.ItemCallback<ListItem>(){
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
