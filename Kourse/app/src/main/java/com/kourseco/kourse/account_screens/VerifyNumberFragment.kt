package com.kourseco.kourse.account_screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kourseco.kourse.R
import com.kourseco.kourse.databinding.FragmentVerifyNumberBinding


class VerifyNumberFragment : Fragment() {

    private lateinit var binding : FragmentVerifyNumberBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_verify_number,container,false)

        return binding.root
    }


}
