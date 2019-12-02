package com.epitech.cashmanager.ui.payment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.epitech.cashmanager.R
import com.epitech.cashmanager.activity.MainActivity
import com.epitech.cashmanager.global.MyApp
import android.app.PendingIntent
import android.content.IntentFilter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.epitech.cashmanager.databinding.FragmentPaymentBinding
import kotlinx.android.synthetic.main.fragment_scan.*


class PaymentFragment : Fragment() {

    private lateinit var pendingIntent: PendingIntent
    private var intentFiltersArray = emptyArray<IntentFilter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentPaymentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)
        var view : View = binding.root
        binding.signalState = MyApp.networkLink
        return view
    }

    override fun onStart() {
        super.onStart()
        // Radio Button Rules
        if (MyApp.isCreditCardSelected && MyApp.isNFCActivate(MyApp.nfcAdapter)) {
            var intent = Intent(activity, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            pendingIntent = PendingIntent.getActivity(context, 0 , intent, 0)
            MyApp.nfcAdapter.enableForegroundDispatch(activity, pendingIntent, intentFiltersArray, null)
        } else if (MyApp.isBankCheckSelected) {
            Toast.makeText(context, "Bank Check", Toast.LENGTH_LONG + 2).show()
        } else {
            Toast.makeText(context, "Miss Something", Toast.LENGTH_LONG + 2).show()
        }
        // Display Total Cart
        if (MyApp.hasNotEmptyCart()) {
            txtTotal.text = "Total : " + MyApp.articleList!![0].total.toString() + "$"
        } else {
            txtTotal.text = "Your Cart is Empty"
        }
    }

    override fun onResume() {
        super.onResume()
//        if (MyApp.isCreditCardSelected && MyApp.isNFCActivate(MyApp.nfcAdapter)) {
//
//            var intent = Intent(activity, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//            pendingIntent = PendingIntent.getActivity(context, 0 , intent, 0)
//            MyApp.nfcAdapter.enableForegroundDispatch(activity, pendingIntent, intentFiltersArray, null)
//
//        } else if (MyApp.isBankCheckSelected) {
//            Toast.makeText(context, "Bank Check", Toast.LENGTH_LONG + 2).show()
//        } else {
//            Toast.makeText(context, "Miss Something", Toast.LENGTH_LONG + 2).show()
//        }
    }

    override fun onPause() {
        super.onPause()
        if (MyApp.isCreditCardSelected && MyApp.isNFCActivate(MyApp.nfcAdapter)) {
            MyApp.nfcAdapter.disableForegroundDispatch(activity)
        }
    }
}