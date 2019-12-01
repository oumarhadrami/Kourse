package com.kourseco.kourse.home_screens.account


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentAccountBinding
import com.kourseco.kourse.databinding.FragmentCartBinding
import com.kourseco.kourse.home_screens.cart.CartViewModel

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var binding : FragmentAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_account,container,false)
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)

        accountViewModel.text.observe(this, Observer {
            binding.textAccount.text = it
        })
        return binding.root
    }


}
