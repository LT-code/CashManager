package com.epitech.cashmanager.ui.scan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.epitech.cashmanager.R
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_scan.*

class ScanFragment : Fragment() {

    private lateinit var scanViewModel: ScanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scanViewModel =
            ViewModelProviders.of(this).get(ScanViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_scan, container, false)
        val textView: TextView = root.findViewById(R.id.text_scan)
        scanViewModel.text.observe(this, Observer {
            textView.text = it
        })

        return root
    }

//    override fun onStart() {
//        super.onStart()
//        btnScanArticles.setOnClickListener {
//            val scanner = IntentIntegrator.forSupportFragment(this)
//            scanner.initiateScan()
//        }
//    }
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