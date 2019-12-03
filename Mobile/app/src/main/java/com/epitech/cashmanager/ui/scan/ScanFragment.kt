package com.epitech.cashmanager.ui.scan

import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    //override fun onStart() {
      //  super.onStart()
        //radioBtnCreditCard.isChecked = MyApp.isCreditCardSelected
        //radioBtnBankCheck.isChecked = MyApp.isBankCheckSelected
        //MyApp.nfcAdapter = NfcAdapter.getDefaultAdapter(context)
        //if (!MyApp.isNFCCompatible(MyApp.nfcAdapter)) {
          //  radioBtnCreditCard.isEnabled = false
          //  radioBtnBankCheck.isChecked = true
        //} else {
          //  if (!MyApp.isNFCActivate(MyApp.nfcAdapter)) {
            //    Toast.makeText(context, "don't forget to activate NFC", Toast.LENGTH_LONG + 2).show()
            //}
       // }
    //}
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