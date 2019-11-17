package com.kourseco.kourse.account_screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentSignUpBinding

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignUpBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)

        binding.closeSignup.setOnClickListener {
            it.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToIntroFragment())
        }

        binding.goToLogin.setOnClickListener {
            it.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToLogInFragment())
        }
        return binding.root
    }


}
