package com.kourseco.kourse.login_screens


import android.app.Activity
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


//        //Make button clickable once 8 number have been registered
//        binding.phoneNumberLogin.addTextChangedListener(object: TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//            override fun afterTextChanged(s: Editable?){
//                if(s.toString().length == 8) {
//                    binding.login.setBackgroundColor(Color.parseColor("#0083b6"))
//                    binding.login.setTextColor(Color.parseColor("#ffffff"))
//                }
//                else {
//                    binding.login.setBackgroundColor(Color.parseColor("#FF7C7979"))
//                    binding.login.setTextColor(Color.parseColor("#000000"))
//                }
//
//            }
//        })

        //Navigate to VerifyPhoneNumber fragment with phone number as argument
        binding.login.setOnClickListener {
            val numberFromEditText = binding.phoneNumberLoginTextfield.text.toString().trim()
            if(numberFromEditText.isEmpty() || numberFromEditText.length < 8){
                binding.phoneNumberLogin.error = "please enter the 8-digit phone number!"
                binding.phoneNumberLogin.requestFocus()
                return@setOnClickListener
            }

            val phoneNumber = "+222$numberFromEditText"
            it.findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToVerifyNumberFragment(phoneNumber))
        }


        return binding.root
    }


}
