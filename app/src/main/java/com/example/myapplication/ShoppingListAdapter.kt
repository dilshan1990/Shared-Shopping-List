package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ShoppingItem

class ShoppingListAdapter(
    private val items: MutableList<ShoppingItem>, // This list manages your items
    private val onDeleteClick: (Int) -> Unit     // Callback for delete action
) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.itemNameTextView)
        val quantityTextView: TextView = view.findViewById(R.id.itemQuantityTextView)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteItemButton)

        fun bind(item: ShoppingItem, deleteFunction: (Int) -> Unit) {
            nameTextView.text = item.name
            quantityTextView.text = item.quantity
            deleteButton.setOnClickListener { deleteFunction(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shopping_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onDeleteClick)
    }

    override fun getItemCount(): Int = items.size

    fun addItem(item: ShoppingItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun removeItem(position: Int) {
        if (position < items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
