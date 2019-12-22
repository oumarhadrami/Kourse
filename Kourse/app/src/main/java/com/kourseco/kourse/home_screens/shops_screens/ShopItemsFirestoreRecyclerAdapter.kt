package com.kourseco.kourse.home_screens.shops_screens

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.ShopitemItemBinding
import com.kourseco.kourse.home_screens.shops_screens.cart_database.CartItem
import kotlinx.coroutines.launch


class ShopItemsFirestoreRecyclerAdapter(
    options: FirestoreRecyclerOptions<ShopItem>,
    activity: FragmentActivity?
) : FirestoreRecyclerAdapter<ShopItem, ShopItemsFirestoreRecyclerAdapter.ViewHolder>(options) {

    //initilize viewmodel
    val application = requireNotNull(activity).application
    val viewModelFactory = ShoppingCartViewModelFactory(application)
    val viewModel = ViewModelProviders.of(activity!!,viewModelFactory).get(ShoppingCartViewModel::class.java)


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
            binding.itemCount.text = item.shopItemCount.toString()
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
                binding.itemCount.text = item.shopItemCount.toString()
                binding.add.visibility = View.GONE
                binding.incdecContainer.visibility = View.VISIBLE

                //Insert cart item if its quantity is 0
                viewModel.viewModelScope.launch {
                    if (!viewModel.recordExists(item.shopItemId)) {
                        val cartItem = getCartItem(item)
                        viewModel.insert(cartItem)
                    }
                }


            }

            binding.addMore.setOnClickListener {
                if (item.shopItemCount < 10)
                item.shopItemCount = item.shopItemCount.plus(1)
                binding.itemCount.text = item.shopItemCount.toString()

                //update quantity of the cart item ++
                val cartItem = getCartItem(item)
                viewModel.update(cartItem)
            }

            binding.removeMore.setOnClickListener {
                if (item.shopItemCount >= 1) {
                    binding.add.visibility = View.GONE
                    item.shopItemCount = item.shopItemCount.minus(1)
                    binding.itemCount.text = item.shopItemCount.toString()

                    //update quantity of the cart item --
                    val cartItem = getCartItem(item)
                    viewModel.update(cartItem)
                }
                if(item.shopItemCount == 0) {
                    binding.itemCount.text = item.shopItemCount.toString()
                    binding.incdecContainer.visibility = View.GONE
                    binding.add.visibility = View.VISIBLE

                    //delete cart item when quantity drops to zero
                    val cartItem = getCartItem(item)
                    viewModel.deleteCartItem(cartItem)
                }
            }

            binding.executePendingBindings()
        }

        // get the the cart item current values
        private fun getCartItem(item: ShopItem): CartItem {
            val cartItem = CartItem(shopItemId = item.shopItemId,
                shopItemName = item.shopItemName,
                shopItemPrice = item.shopItemPrice,
                shopItemQuantity = item.shopItemCount)

            return cartItem
        }


    }
    private fun from(parent: ViewGroup): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShopitemItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }
}


