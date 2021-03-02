package com.gadrien456.gmail.tp3_adrien_guedes.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gadrien456.gmail.tp3_adrien_guedes.R
import com.gadrien456.gmail.tp3_adrien_guedes.databinding.NeighborItemBinding
import com.gadrien456.gmail.tp3_adrien_guedes.models.Neighbor

class ListNeighborsAdapter(
    items: List<Neighbor>,
    val callback: ListNeighborHandler
) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {
    private val mNeighbours: List<Neighbor> = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: NeighborItemBinding = NeighborItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        // Display Neighbour Name
        holder.binding.itemListDeleteButton.setOnClickListener {

            callback.onDeleteNeighbor(neighbour)
        }

// Display Neighbour Avatar
        Log.d("item", neighbour.name)
        holder.binding.itemListName.text = neighbour.name
        val context = holder.binding.root.context
        Glide.with(context)
            .load(neighbour.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .skipMemoryCache(false)
            .into(holder.binding.itemListAvatar)
    }

    override fun getItemCount(): Int {
        var itemCount = 0
        if (mNeighbours != null) {
            itemCount = mNeighbours!!.size
        }
        return itemCount
    }

    class ViewHolder(val binding: NeighborItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
