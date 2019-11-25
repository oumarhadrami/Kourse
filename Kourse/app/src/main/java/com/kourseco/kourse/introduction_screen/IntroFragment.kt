package com.kourseco.kourse.introduction_screen


import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentIntroBinding

/**
 * A simple [Fragment] subclass.
 */
class IntroFragment : Fragment() {

    private lateinit var binding : FragmentIntroBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_intro,container,false)

        // setting up adapter and tabLayout with viewPager
        val layoutAdapter = LayoutPagerAdapter(layoutInflater)
        binding.introViewpager.adapter = layoutAdapter
        binding.tabLayout.setupWithViewPager(binding.introViewpager)


        //Make the Login part of the text orange
        //makeLoginOrange()

        // Naviagte to LogIn page
        binding.loginButton.setOnClickListener {
            it.findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToLogInFragment())
        }





        return binding.root
    }

    /*private fun makeLoginOrange() {
        val text = "Have an account? Login"
        val ss = SpannableString(text)
        val orangeLogin = ForegroundColorSpan(Color.parseColor("#0083b6"))
        ss.setSpan(orangeLogin, 17,22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.loginButton.text = ss

    }*/


}
