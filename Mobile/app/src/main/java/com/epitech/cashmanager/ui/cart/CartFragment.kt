package com.epitech.cashmanager.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.epitech.cashmanager.R
import com.epitech.cashmanager.databinding.FragmentCartBinding
import com.epitech.cashmanager.global.MyApp



class CartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var binding : FragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        var view : View = binding.root
        binding.signalState = MyApp.networkLink
        return view
    }
}