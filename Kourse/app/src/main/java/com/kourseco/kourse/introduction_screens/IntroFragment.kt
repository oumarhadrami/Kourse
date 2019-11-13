package com.kourseco.kourse.introduction_screens


import android.os.Bundle
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentIntroBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_intro,container,false)

        val layoutAdapter = LayoutPagerAdapter(layoutInflater)
        binding.introViewpager.adapter = layoutAdapter
        return binding.root
    }


}
