package com.kourseco.kourse.introduction_screens


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

import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentIntroBinding

/**
 * A simple [Fragment] subclass.
 */
class IntroFragment : Fragment() {

    private lateinit var binding : FragmentIntroBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_intro,container,false)
        val layoutAdapter = LayoutPagerAdapter(layoutInflater)
        binding.introViewpager.adapter = layoutAdapter
        binding.tabLayout.setupWithViewPager(binding.introViewpager)


        //code to make the Login part of the text orange
        makeLoginOrange()




        return binding.root
    }

    private fun makeLoginOrange() {
        val text = "Have an account? Login"
        val ss : SpannableString = SpannableString(text)
        val orangeLogin : ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#fc8019"))
        ss.setSpan(orangeLogin, 17,22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.loginButton.text = ss

    }


}
