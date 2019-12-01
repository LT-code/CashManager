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
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.card.MaterialCardView

class CartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var binding : FragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        var view : View = binding.root
        binding.signalState = MyApp.networkLink
        return view
    }

    override fun onStart() {
        super.onStart()
        CreateCardViewProgrammatically()
    }

    fun CreateCardViewProgrammatically() {
        var mainlayout = view!!.findViewById<LinearLayout>(R.id.linearLayoutCart)

        // INIT MATERIAL CARD
        var materialCardView = MaterialCardView(context)
        var materialCardViewParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 249) // 83dp
        materialCardViewParam.setMargins(24, 24, 24, 0) // 8dp
        materialCardView.layoutParams = materialCardViewParam

        // Article Name
        var txtArticleName = TextView(context)
        val txtArticleNameParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, // 128dp
            120 // 40dp
        )
        txtArticleName.layoutParams = txtArticleNameParam
        txtArticleName.text = "DYNAMIC TEST 1 Article Name"
        materialCardView.addView(txtArticleName)

        // Article Price
        var txtArticlePrice = TextView(context)
        val txtArticlePriceParam = LinearLayout.LayoutParams(540, // 180dp
            108 // 36dp
        )
        txtArticlePriceParam.setMargins(0, 135, 0,0) // 45dp
        txtArticlePrice.layoutParams = txtArticlePriceParam
        txtArticlePrice.text = "DYNAMIC TEST 1 Article Price"
        materialCardView.addView(txtArticlePrice)

        // Article Qte
        var txtArticleQte = TextView(context)
        val txtArticleQteParam = LinearLayout.LayoutParams(540, // 180dp
            108 // 36dp
        )
        txtArticleQteParam.setMargins(561, 135, 0,0) // 187dp & 45dp
        txtArticleQte.layoutParams = txtArticleQteParam
        txtArticleQte.text = "DYNAMIC TEST 1 Article Qte"
        materialCardView.addView(txtArticleQte)

        mainlayout.addView(materialCardView)

    }
}