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
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.contains
import androidx.core.view.isEmpty
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
        var mainlayout = view!!.findViewById<LinearLayout>(R.id.linearLayoutCart)
        if (mainlayout.childCount <= 0) {
            // If Empty We Create Material Card
            craftMaterialCard()
        }
    }

    fun craftMaterialCard() {
        var mainlayout = view!!.findViewById<LinearLayout>(R.id.linearLayoutCart)
        var txtTotal = view!!.findViewById<TextView>(R.id.txtTotal)

        if (MyApp.hasNotEmptyCart()) {
            txtTotal.text = "Total : "  + MyApp.articleList!![0].total.toString() + "$"
            for (i in MyApp.articleList!![0].listArticleCart.indices) {
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
                txtArticleName.text = "Article : " + MyApp.articleList!![0].listArticleCart[i]!!.article.name
                materialCardView.addView(txtArticleName)

                // Article Price
                var txtArticlePrice = TextView(context)
                val txtArticlePriceParam = LinearLayout.LayoutParams(540, // 180dp
                    108 // 36dp
                )
                txtArticlePriceParam.setMargins(0, 135, 0,0) // 45dp
                txtArticlePrice.layoutParams = txtArticlePriceParam
                txtArticlePrice.text = "Price : " + MyApp.articleList!![0].listArticleCart[i]!!.article.price.toString() + "$"
                materialCardView.addView(txtArticlePrice)

                // Article Qte
                var txtArticleQte = TextView(context)
                val txtArticleQteParam = LinearLayout.LayoutParams(540, // 180dp
                    108 // 36dp
                )
                txtArticleQteParam.setMargins(561, 135, 0,0) // 187dp & 45dp
                txtArticleQte.layoutParams = txtArticleQteParam
                txtArticleQte.text = "Quantity : x" + MyApp.articleList!![0].listArticleCart[i]!!.quantity.toString()
                materialCardView.addView(txtArticleQte)
                mainlayout.addView(materialCardView)
            }
        } else {
            txtTotal.text = "Your Cart is Empty"
        }
    }

//    fun dynamicTest() {
//        // Article Name
//        var txtArticleName = TextView(context)
//        val txtArticleNameParam = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT, // 128dp
//            120 // 40dp
//        )
//        txtArticleName.layoutParams = txtArticleNameParam
//        txtArticleName.text = "Article : DYNAMIC TEST 1"
//        materialCardView.addView(txtArticleName)
//
//        // Article Price
//        var txtArticlePrice = TextView(context)
//        val txtArticlePriceParam = LinearLayout.LayoutParams(540, // 180dp
//            108 // 36dp
//        )
//        txtArticlePriceParam.setMargins(0, 135, 0,0) // 45dp
//        txtArticlePrice.layoutParams = txtArticlePriceParam
//        txtArticlePrice.text = "Price : 14.99$"
//        materialCardView.addView(txtArticlePrice)
//
//        // Article Qte
//        var txtArticleQte = TextView(context)
//        val txtArticleQteParam = LinearLayout.LayoutParams(540, // 180dp
//            108 // 36dp
//        )
//        txtArticleQteParam.setMargins(561, 135, 0,0) // 187dp & 45dp
//        txtArticleQte.layoutParams = txtArticleQteParam
//        txtArticleQte.text = "Quantity : x5"
//        materialCardView.addView(txtArticleQte)
//    }
}