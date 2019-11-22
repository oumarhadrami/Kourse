package com.kourseco.kourse.account_screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentVerifyNumberBinding
import java.util.*


class VerifyNumberFragment : Fragment() {


    private lateinit var binding: FragmentVerifyNumberBinding
    private lateinit var auth : FirebaseAuth



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify_number, container, false)

        //Initialize firebase Auth
        auth = FirebaseAuth.getInstance()
        auth.setLanguageCode(Locale.getDefault().language)

        // Get phone number from previous fragment
        val args = VerifyNumberFragmentArgs.fromBundle(arguments!!)
        val phoneNumber = args.phoneNumber
        binding.phoneNumberVerification.text = phoneNumber



        return binding.root
    }

}








