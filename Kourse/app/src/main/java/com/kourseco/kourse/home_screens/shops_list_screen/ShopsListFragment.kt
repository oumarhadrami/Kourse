package com.kourseco.kourse.home_screens.shops_list_screen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentShopsListBinding
import com.kourseco.kourse.util.FirestoreUtil


class ShopsListFragment : Fragment() {

    private lateinit var binding : FragmentShopsListBinding
    private lateinit var adapter : ShopFirestoreRecyclerAdapter
    private lateinit var shopsDocRef : CollectionReference


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shops_list,container,false)

        //get shopType from home fragment
        val args =
            ShopsListFragmentArgs.fromBundle(arguments!!)

        shopsDocRef = FirestoreUtil.firestoreInstance.collection("Shops/"+ args.shopType + "/" + args.shopType)
        val options = FirestoreRecyclerOptions.Builder<Shop>().setQuery(shopsDocRef, Shop::class.java).build()
        adapter = ShopFirestoreRecyclerAdapter(options)
        binding.shopsList.adapter = adapter
        binding.lifecycleOwner = this



        return binding.root
    }


    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

}
