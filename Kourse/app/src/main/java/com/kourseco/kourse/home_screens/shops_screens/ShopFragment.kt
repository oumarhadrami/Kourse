package com.kourseco.kourse.home_screens.shops_screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference

import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentShopBinding
import com.kourseco.kourse.util.FirestoreUtil

class ShopFragment : Fragment() {
    private lateinit var binding : FragmentShopBinding
    private lateinit var shopItemsRef : CollectionReference
    private lateinit var adapter : ShopItemsFirestoreRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shop,container,false)

        //binding shop details to the views
        val args = ShopFragmentArgs.fromBundle(arguments!!)
//        FirestoreUtil.firestoreInstance
//            .document(""+args.ref)
//            .get()
//            .addOnSuccessListener {
//                Log.i("docQQ",""+it["shopRating"])}

        // shop items reference and recyclerview adapter init
        val collectionPath = args.ref + "/Items"
        shopItemsRef = FirestoreUtil.firestoreInstance.collection(collectionPath)
        val options = FirestoreRecyclerOptions.Builder<ShopItem>().setQuery(shopItemsRef, ShopItem::class.java).build()
        val manager = GridLayoutManager(activity, 2)
        adapter = ShopItemsFirestoreRecyclerAdapter(options)
        binding.itemsList.layoutManager = manager
        binding.itemsList.adapter = adapter
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
