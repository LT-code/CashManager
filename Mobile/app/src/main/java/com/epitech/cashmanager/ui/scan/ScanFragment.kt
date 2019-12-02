package com.epitech.cashmanager.ui.scan

import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.epitech.cashmanager.R
import com.epitech.cashmanager.databinding.FragmentScanBinding
import com.epitech.cashmanager.global.MyApp
import kotlinx.android.synthetic.main.fragment_scan.*

class ScanFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var binding : FragmentScanBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_scan, container, false)
        var view : View = binding.root
        binding.signalState = MyApp.networkLink
        return view
    }

    override fun onStart() {
        super.onStart()
        // Radio Button Rules
        radioBtnCreditCard.isChecked = MyApp.isCreditCardSelected
        radioBtnBankCheck.isChecked = MyApp.isBankCheckSelected
        var txtPaymentMethod = view!!.findViewById<TextView>(R.id.txtPaymentMethod)
        if (MyApp.isCreditCardSelected) {
            txtPaymentMethod.text = "Credit Card (NFC)"
        } else if (MyApp.isBankCheckSelected) {
            txtPaymentMethod.text = "Bank Check (QR Code)"
        } else {
            txtPaymentMethod.text = "Choose your Payment Method"
        }
        // NFC Rules
        MyApp.nfcAdapter = NfcAdapter.getDefaultAdapter(context)
        if (!MyApp.isNFCCompatible(MyApp.nfcAdapter)) {
            radioBtnCreditCard.isEnabled = false
            radioBtnBankCheck.isChecked = true
        } else {
            if (!MyApp.isNFCActivate(MyApp.nfcAdapter)) {
                Toast.makeText(context, "don't forget to activate NFC", Toast.LENGTH_LONG + 2).show()
            }
        }
        // Display Total Cart
        if (MyApp.hasNotEmptyCart()) {
            txtTotal.text = "Total : " + MyApp.articleList!![0].total.toString() + "$"
        } else {
            txtTotal.text = "Your Cart is Empty"
        }
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == Activity.RESULT_OK) {
//            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//            if (result != null) {
//                if (result.contents == null) {
//                    Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG + 2).show()
//                } else {
//                    Toast.makeText(context, "Scanned: " + result.contents, Toast.LENGTH_LONG + 2).show()
//                }
//            } else {
//                super.onActivityResult(requestCode, resultCode, data)
//            }
//        }
//    }
}