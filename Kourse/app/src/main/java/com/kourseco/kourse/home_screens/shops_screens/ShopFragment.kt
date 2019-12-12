package com.kourseco.kourse.home_screens.shops_screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentShopBinding


class ShopFragment : Fragment() {

    private lateinit var binding : FragmentShopBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shop,container,false)

        //get shopType from home fragment
        val args = ShopFragmentArgs.fromBundle(arguments!!)
        val shopId = args.shopId
        Toast.makeText(binding.root.context,shopId,Toast.LENGTH_SHORT).show()


        return binding.root
    }


}
