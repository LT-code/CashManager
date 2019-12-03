package com.epitech.cashmanager.activity

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.MifareUltralight
import android.nfc.tech.Ndef
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.epitech.cashmanager.R
import com.epitech.cashmanager.global.MyApp
import com.google.zxing.integration.android.IntentIntegrator
import android.os.Parcelable
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import com.epitech.cashmanager.model.requestModel.CartArticleRequest
import com.epitech.cashmanager.model.requestModel.PayRequest
import com.epitech.cashmanager.model.requestModel.PaymentMethodRequest
import com.epitech.cashmanager.model.responseModel.ArticleResponse
import com.epitech.cashmanager.model.responseModel.CartResponse
import com.epitech.cashmanager.model.responseModel.PaymentMethodResponse
import com.epitech.cashmanager.service.CartService
import com.epitech.cashmanager.service.PaymentService
import com.google.zxing.integration.android.IntentResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        MyApp.deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }

    private val networkChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            MyApp.hasNetworkAccess = MyApp.networkLink.isInternetAvailable(applicationContext)
            Log.d("NETWORK", MyApp.hasNetworkAccess.toString())
            MyApp.refreshConnexionState()
            Log.d("serverLink", MyApp.networkLink.serverLink.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        @Suppress("DEPRECATION")
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkChangeReceiver)
    }

    fun goToSettings(view: View) {
        val settingsIntent = Intent(this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

    fun onRadioButtonClicked(view: View) {
        var txtPaymentMethod = findViewById<TextView>(R.id.txtPaymentMethod)
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioBtnCreditCard ->
                    if (checked) {
                        // Credit Card Code
                        MyApp.isCreditCardSelected = true
                        MyApp.isBankCheckSelected = false
                        txtPaymentMethod.text = "Credit Card (NFC)"
                        MyApp.paymentMethodResponse = null
                    }
                R.id.radioBtnBankCheck ->
                    if (checked) {
                        // Bank Check Code
                        MyApp.isBankCheckSelected = true
                        MyApp.isCreditCardSelected = false
                        txtPaymentMethod.text = "Bank Check (QR Code)"
                        MyApp.paymentMethodResponse = null
                    }
            }
        }
    }

    fun launchArticleScan(view: View) {
        if (MyApp.networkLink!!.serverLink) {
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES) // Only Barcode
            scanner.initiateScan()
        } else {
            Toast.makeText(this, "Establish Connexion First", Toast.LENGTH_LONG + 2).show()
        }
    }

    fun launchCheckScan(view: View) {
        if (MyApp.networkLink!!.serverLink) {
            if (MyApp.hasNotEmptyCart()) {
                val scanner = IntentIntegrator(this)
                scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE) // Only QRCode
                scanner.initiateScan()
            } else {
                Toast.makeText(this, "Scan Article First", Toast.LENGTH_LONG + 2).show()
            }
        } else {
            Toast.makeText(this, "Establish Connexion First", Toast.LENGTH_LONG + 2).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null && result.formatName != "QR_CODE") {
                if (result.contents != null) {
                    Toast.makeText(this, "Search For : " + result.contents, Toast.LENGTH_LONG + 2).show()
                    addArticleToCart(result)
                } else {
                    Toast.makeText(this, "Cancelled (Barcode Empty)", Toast.LENGTH_LONG + 2).show()
                }
            } else if (result != null && result.formatName == "QR_CODE") {
                if (result.contents != null) {
                    Toast.makeText(this, "QR Code : " + result.contents, Toast.LENGTH_LONG + 2).show()
                    MyApp.actualBankCheckScanned = result.contents
                    scanBankCheck(result)
                } else {
                    Toast.makeText(this, "Cancelled (QRCode Empty)", Toast.LENGTH_LONG + 2).show()
                }
            }
        }
    }

    fun scanBankCheck(intentResult: IntentResult){
        // Call Back Payment Choice
        var paymentMethodRequest = PaymentMethodRequest(MyApp.machineSettingSession!![0].currentIdCart, 2)
        val service = MyApp.networkInstance!!.retrofit.create(PaymentService::class.java)
        val choosePaymentMethod : Call<PaymentMethodResponse> = service.choosePaymentMethod(
            MyApp.machineSettingSession!![0].machine.token!!, paymentMethodRequest)

        choosePaymentMethod.enqueue(object : Callback<PaymentMethodResponse> {
            override fun onResponse(call: Call<PaymentMethodResponse>, response: Response<PaymentMethodResponse>) {
                if (response.isSuccessful) {
                    MyApp.paymentMethodResponse = response.body()!!.data
                    Toast.makeText(applicationContext, "SET PAYMENT CHOICE OK", Toast.LENGTH_LONG).show()
                    findViewById<Button>(R.id.btnPay).visibility = View.VISIBLE
                } else {
                    findViewById<Button>(R.id.btnPay).visibility = View.INVISIBLE
                    Log.e("PAYMENT CHOICE FAILED", response.errorBody()!!.string())
                    Toast.makeText(applicationContext, "SET PAYMENT CHOICE FAILED" + response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<PaymentMethodResponse>, t: Throwable) {
                Log.e("PAYMENT CHOICE FAILED", "Error : $t")
                findViewById<Button>(R.id.btnPay).visibility = View.INVISIBLE
                Toast.makeText(applicationContext, "SET PAYMENT CHOICE FAILED - Technical Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun payWithBankCheck(view: View) {
        // Call Back Payment
        var payRequest = PayRequest(MyApp.machineSettingSession!![0].currentIdCart, MyApp.actualBankCheckScanned!!)
        val service = MyApp.networkInstance!!.retrofit.create(PaymentService::class.java)
        val payWithBankCheck : Call<PaymentMethodResponse> = service.payWithBankCheck(
            MyApp.machineSettingSession!![0].machine.token!!, payRequest)

        payWithBankCheck.enqueue(object : Callback<PaymentMethodResponse> {
            override fun onResponse(call: Call<PaymentMethodResponse>, response: Response<PaymentMethodResponse>) {
                if (response.isSuccessful) {
                    MyApp.paymentMethodResponse!![0].numberAttempts += 1
                    Toast.makeText(applicationContext, "PAYMENT OK", Toast.LENGTH_LONG).show()
                    // Todo refresh all
                } else {
                    MyApp.paymentMethodResponse!![0].numberAttempts += 1
                    if (MyApp.paymentMethodResponse!![0].numberAttempts > 2) {
                        findViewById<Button>(R.id.btnPay).isEnabled = false
                        Toast.makeText(applicationContext, "Attempt Reach : Can't pay !", Toast.LENGTH_LONG).show()
                    }
                    Log.e("PAYMENT FAILED", response.errorBody()!!.string())
                    Toast.makeText(applicationContext, "PAYMENT FAILED : Attempt " +
                            MyApp.paymentMethodResponse!![0].numberAttempts.toString() + "/3", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<PaymentMethodResponse>, t: Throwable) {
                Log.e("PAYMENT FAILED", "Error : $t")
                Toast.makeText(applicationContext, "PAYMENT FAILED - Technical Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun addArticleToCart(intentResult: IntentResult){
        var articleCartRequest = CartArticleRequest(MyApp.machineSettingSession!![0].currentIdCart, intentResult.contents)
        val service = MyApp.networkInstance!!.retrofit.create(CartService::class.java)
        val addArticleRequest: Call<ArticleResponse> = service.addOneArticle(
            MyApp.machineSettingSession!![0].machine.token!!, articleCartRequest)

        addArticleRequest.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "ARTICLE IS ADD", Toast.LENGTH_LONG).show()
                    getShoppingCart()
                } else {
                    Log.e("ADD ARTICLE FAILED", response.errorBody()!!.string())
                    Toast.makeText(applicationContext, "ADD ARTICLE FAILED" + response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Log.e("ADD ARTICLE FAILED", "Error : $t")
                Toast.makeText(applicationContext, "ADD ARTICLE FAILED - Technical Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getShoppingCart() {
        val service = MyApp.networkInstance!!.retrofit.create(CartService::class.java)
        val connectRequest: Call<CartResponse> = service.getCartArticles(
            MyApp.machineSettingSession!![0].machine.token!!, MyApp.machineSettingSession!![0].currentIdCart)

        connectRequest.enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    MyApp.articleList = response.body()!!.data
                    Toast.makeText(applicationContext, "CART IS UPDATE", Toast.LENGTH_LONG).show()
                } else {
                    Log.e("GET Cart Failed", response.errorBody()!!.string())
                    Toast.makeText(applicationContext, "GET Cart Failed" + response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                Log.e("GET Cart Failed", "Error : $t")
                Toast.makeText(applicationContext, "GET Cart Failed - Technical Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            val tag : Tag? = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)
            var test = MifareUltralight.get(tag)?.use { mifare ->
                mifare.connect()
                val payload = mifare.readPages(4)
                String(payload, Charset.forName("US-ASCII"))
            }
            Toast.makeText(this, "J'ai pas eu mieux ^^" + test.toString(), Toast.LENGTH_LONG + 5).show()
            Log.d("J'ai pas eu mieux ^^", test.toString())

//            var techList = tag.techList
//            var searchedTech : String = Ndef::class.java.name
//            for (tech in techList) {
//                if (searchedTech == tech) {
//                    Toast.makeText(this, "NFC :" + tag.describeContents().toString(), Toast.LENGTH_LONG + 2).show()
//                    MyApp.nfcReader.execute(tag)
//                    break
//                }
//            }
        }
    }

}
