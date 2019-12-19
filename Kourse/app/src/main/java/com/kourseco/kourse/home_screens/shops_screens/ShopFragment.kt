package com.kourseco.kourse.home_screens.shops_screens


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentShopBinding
import com.kourseco.kourse.util.FirestoreUtil

class ShopFragment : Fragment() {
    private lateinit var binding : FragmentShopBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shop,container,false)

        //binding shop details to the views
        val args = ShopFragmentArgs.fromBundle(arguments!!)
        FirestoreUtil.firestoreInstance
            .document(""+args.ref)
            .get()
            .addOnSuccessListener {
                Log.i("docQQ",""+it["shopRating"])}




        return binding.root
    }


}
