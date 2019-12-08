package com.kourseco.kourse


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.kourseco.kourse.databinding.FragmentShopBinding
import kotlinx.android.synthetic.main.fragment_shop.*


class ShopFragment : Fragment() {

    private lateinit var binding : FragmentShopBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shop,container,false)

        //get shopType from home fragment
        val args = ShopFragmentArgs.fromBundle(arguments!!)
        val shopName = args.shopName


        return binding.root
    }


}
