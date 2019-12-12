package com.kourseco.kourse


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.kourseco.kourse.databinding.FragmentShopsListBinding

/**
 * A simple [Fragment] subclass.
 */
class ShopsListFragment : Fragment() {

    private lateinit var binding : FragmentShopsListBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shops_list,container,false)

        //get shopType from home fragment
        val args = ShopsListFragmentArgs.fromBundle(arguments!!)

        when(args.shopType){
            1-> Toast.makeText(activity,"supermarkets", Toast.LENGTH_SHORT).show()
            2-> Toast.makeText(activity, "restaurants", Toast.LENGTH_SHORT).show()
            3-> Toast.makeText(activity, "laitieres", Toast.LENGTH_SHORT).show()
            4-> Toast.makeText(activity, "bakeries", Toast.LENGTH_SHORT).show()
            5-> Toast.makeText(activity, "pharmacies", Toast.LENGTH_SHORT).show()
            6-> Toast.makeText(activity, "boutique", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }


}
