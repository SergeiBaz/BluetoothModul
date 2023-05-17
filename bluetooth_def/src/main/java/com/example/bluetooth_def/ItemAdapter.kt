package com.example.bluetooth_def

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bluetooth_def.databinding.ListItemBinding

class ItemAdapter(private val listener: Listener) :
    ListAdapter<ListItem, ItemAdapter.MyHolder>(Comparator()) {
    private var oldCheckBox: CheckBox? = null

    class MyHolder(
        view: View, private val adapter: ItemAdapter,
        listener: Listener
    ) : RecyclerView.ViewHolder(view) {
        private var binding = ListItemBinding.bind(view)
        private var device: ListItem? = null

        init {
            binding.checkBox.setOnClickListener {
                device?.let { it1 -> listener.onClick(it1) }
                adapter.selectCheckBox(binding.checkBox)
            }

            itemView.setOnClickListener {
                device?.let { it1 -> listener.onClick(it1) }
                adapter.selectCheckBox(binding.checkBox)
            }
        }

        fun bind(item: ListItem) = with(binding) {
            device = item
            name.text = item.nameDevises
            mac.text = item.macAddress
        }
    }

    class Comparator : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyHolder(view, this, listener)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun selectCheckBox(checkBox: CheckBox) {
        oldCheckBox?.isChecked = false
        oldCheckBox = checkBox
        oldCheckBox?.isChecked = true
    }

    interface Listener {
        fun onClick(device: ListItem)
    }

}