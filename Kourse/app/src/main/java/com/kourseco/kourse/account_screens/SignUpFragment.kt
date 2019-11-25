package com.kourseco.kourse.account_screens


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
import com.kourseco.kourse.databinding.FragmentSignUpBinding

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    private lateinit var binding : FragmentSignUpBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up,container,false)

        //make keyboard appear
        binding.nameSignup.requestFocus()
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)




        //pass name, email, number
        binding.signup.setOnClickListener {
            val nameFromEditText = binding.nameSignup.text.toString().trim()
            val emailFromEditText = binding.emailSignup.text.toString().trim()
            val numberFromEditText = binding.phoneNumberSignup.text.toString().trim()
            if(nameFromEditText.isEmpty()){
                binding.nameSignup.error = "please enter phone name!"
                binding.nameSignup.requestFocus()
                return@setOnClickListener
            }
            if(emailFromEditText.isEmpty()){
                binding.emailSignup.error = "please enter email!"
                binding.emailSignup.requestFocus()
                return@setOnClickListener
            }
            if(numberFromEditText.isEmpty()){
                binding.phoneNumberSignup.error = "please enter phone number!"
                binding.phoneNumberSignup.requestFocus()
                return@setOnClickListener
            }

            val phoneNumber = "+222$numberFromEditText"
            //it.findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToVerifyNumberFragment(phoneNumber,nameFromEditText,emailFromEditText))
        }
        return binding.root
    }


}
