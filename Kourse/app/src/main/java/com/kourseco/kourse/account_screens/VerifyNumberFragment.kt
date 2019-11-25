package com.kourseco.kourse.account_screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.kourseco.kourse.HomeActivity
import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentVerifyNumberBinding
import kotlinx.android.synthetic.main.fragment_verify_number.*
import java.util.*
import java.util.concurrent.TimeUnit


class VerifyNumberFragment : Fragment() {


    private lateinit var binding: FragmentVerifyNumberBinding
    private lateinit var auth : FirebaseAuth
    lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationId = ""



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_verify_number, container, false)

        //Initialize firebase Auth
        auth = FirebaseAuth.getInstance()
        auth.setLanguageCode(Locale.getDefault().language)

        // Get phone number from previous fragment
        val args = VerifyNumberFragmentArgs.fromBundle(arguments!!)
        val phoneNumber = args.phoneNumber
        binding.phoneNumberVerification.text = phoneNumber


        //start the phone verification process
        verifyPhoneNumber(phoneNumber)

        //validate otp Manually
        binding.enterOtpButton.setOnClickListener {
            authenticateManually()
        }





        return binding.root
    }

    private fun authenticateManually() {

        val enteredCode = binding.otpView.text.toString()
        if (enteredCode.isEmpty()   || enteredCode.length < 6) {
            binding.otpView.error = "Enter the 6-digit code..."
            binding.otpView.requestFocus()
            return
        }
            val credential = PhoneAuthProvider.getCredential(verificationId, enteredCode)
            signIn(credential)

    }

    private fun verifyPhoneNumber(phoneNumber: String) {

        verificationCallbacks()

        activity?.let {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                it,
                callbacks
            )
        }
    }

    private fun verificationCallbacks(){
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                Toast.makeText(activity, "success", Toast.LENGTH_LONG).show()
                binding.otpView.setText(p0.smsCode)
                signIn(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(activity, p0.message, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                if (p0 != null){
                    //binding.otpView.setText(p0)
                    verificationId = p0
                }

                binding.progressBar.visibility = View.GONE
                Toast.makeText(activity, "code sent", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun signIn(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful)
                startActivity(Intent(activity, HomeActivity::class.java))
            else
                Toast.makeText(activity, "Phone number not verified!!",Toast.LENGTH_LONG).show()
            }
    }

}








