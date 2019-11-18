package com.kourseco.kourse.account_screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentLogInBinding

/**
 * A simple [Fragment] subclass.
 */
class LogInFragment : Fragment() {
    private lateinit var binding : FragmentLogInBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_log_in,container,false)


        //Navigate to Intro
        binding.closeLogin.setOnClickListener {
            it.findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToIntroFragment())
        }

        //Navigate to signUp page
        binding.goToSignup.setOnClickListener {
            it.findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToSignUpFragment())
        }
        return binding.root
    }


}
