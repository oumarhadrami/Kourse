package com.kourseco.kourse.home_screens.shops_screens

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.ShopitemItemBinding


class ShopItemsFirestoreRecyclerAdapter(options: FirestoreRecyclerOptions<ShopItem>) : FirestoreRecyclerAdapter<ShopItem, ShopItemsFirestoreRecyclerAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, p2: ShopItem) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder  constructor(val binding: ShopitemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: ShopItem) {
            binding.shopItemName.text = item.shopItemName
            binding.shopItemPrice.text = item.shopItemPrice
            Glide.with(binding.shopItemImage.context)
                .load(item.shopItemImage)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(binding.shopItemImage)


            binding.add.setOnClickListener {
                item.shopItemCount = item.shopItemCount.plus(1)
                Log.i("hey", ""+item.shopItemCount)
                binding.itemCount.text = item.shopItemCount.toString()
            }

            binding.addMore.setOnClickListener {
                if (item.shopItemCount > 10)
                    return@setOnClickListener
                item.shopItemCount = item.shopItemCount.plus(1)
                binding.itemCount.text = item.shopItemCount.toString()
            }

            binding.removeMore.setOnClickListener {
                if (item.shopItemCount == 0)
                    return@setOnClickListener
                item.shopItemCount = item.shopItemCount.plus(1)
                binding.itemCount.text = item.shopItemCount.toString()
            }

            binding.executePendingBindings()
        }


    }
    private fun from(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShopitemItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }
}


