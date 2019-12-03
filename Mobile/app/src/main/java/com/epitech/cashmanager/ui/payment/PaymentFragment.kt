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
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
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
        var txtPaymentScan = view!!.findViewById<TextView>(R.id.txtPaymentScan)
        // Radio Button Rules
        if (MyApp.isCreditCardSelected) {
            view!!.findViewById<ImageButton>(R.id.btnBankCheckScan).visibility = View.GONE
            if (MyApp.isNFCActivate(MyApp.nfcAdapter)) {
                txtPaymentScan.text = "Please put your phone near your Credit Card to Scan"
                var intent = Intent(activity, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                pendingIntent = PendingIntent.getActivity(context, 0 , intent, 0)
                MyApp.nfcAdapter!!.enableForegroundDispatch(activity, pendingIntent, intentFiltersArray, null)
            } else {
                txtPaymentScan.text = "Make Sure NFC is activate"
            }
        } else if (MyApp.isBankCheckSelected) {
            txtPaymentScan.text = "Press to Scan your Bank Check QRCode"
            view!!.findViewById<ImageButton>(R.id.btnBankCheckScan).visibility = View.VISIBLE

        } else {
            txtPaymentScan.text = "You have to Choose a Payment Method First"
            view!!.findViewById<ImageButton>(R.id.btnBankCheckScan).visibility = View.GONE
        }
        // Display Total Cart
        if (MyApp.hasNotEmptyCart()) {
            txtTotal.text = "Total : " + MyApp.articleList!![0].total.toString() + "$"
            if (!MyApp.paymentMethodResponse.isNullOrEmpty() && MyApp.paymentMethodResponse!![0].idType != null) {
                view!!.findViewById<Button>(R.id.btnPay).visibility = View.VISIBLE
                if (MyApp.paymentMethodResponse!![0].numberAttempts > 2) {
                    view!!.findViewById<Button>(R.id.btnPay).isEnabled = false
                }
            } else {
                view!!.findViewById<Button>(R.id.btnPay).visibility = View.INVISIBLE
            }
        } else {
            txtTotal.text = "Your Cart is Empty"
        }
    }

    override fun onPause() {
        super.onPause()
        if (MyApp.isCreditCardSelected && MyApp.isNFCActivate(MyApp.nfcAdapter)) {
            MyApp.nfcAdapter.disableForegroundDispatch(activity)
        }
    }
}