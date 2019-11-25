package com.kourseco.kourse.account_screens


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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


        //make keyboard appear
        binding.phoneNumberLogin.requestFocus()
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)

        //Navigate to Intro
        binding.closeLogin.setOnClickListener {
            it.findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToIntroFragment())
        }




        binding.login.setOnClickListener {
            val numberFromEditText = binding.phoneNumberLogin.text.toString().trim()
            if(numberFromEditText.isEmpty()){
                binding.phoneNumberLogin.error = "please enter phone number!"
                binding.phoneNumberLogin.requestFocus()
                return@setOnClickListener
            }

            val phoneNumber = "+222$numberFromEditText"
            it.findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToVerifyNumberFragment(phoneNumber))
        }


        return binding.root
    }


}
