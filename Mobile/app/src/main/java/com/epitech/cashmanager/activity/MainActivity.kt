package com.epitech.cashmanager.activity

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.nfc.NfcAdapter
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
import com.epitech.cashmanager.model.Article
import com.epitech.cashmanager.model.requestModel.CartArticleRequest
import com.epitech.cashmanager.model.requestModel.MachineSettingRequest
import com.epitech.cashmanager.model.responseModel.ArticleResponse
import com.epitech.cashmanager.model.responseModel.MachineSettingResponse
import com.epitech.cashmanager.service.ArticleService
import com.epitech.cashmanager.service.CartService
import com.epitech.cashmanager.service.MachineService
import com.google.zxing.integration.android.IntentResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
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
                    }
                R.id.radioBtnBankCheck ->
                    if (checked) {
                        // Bank Check Code
                        MyApp.isBankCheckSelected = true
                        MyApp.isCreditCardSelected = false
                    }
            }
        }
    }

    fun launchScan(view: View) {
        val scanner = IntentIntegrator(this)
        scanner.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG + 2).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG + 2).show()
                    addArticleToCart(result)
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    fun addArticleToCart(intentResult: IntentResult){
        var articleCartRequest = CartArticleRequest(MyApp.machineSettingSession!![0].currentIdCart, intentResult.contents)
        val service = MyApp.networkInstance!!.retrofit.create(CartService::class.java)
        val addArticleRequest: Call<ArticleResponse> = service.addOneArticle(
            MyApp.machineSettingSession!![0].machine.token!!, articleCartRequest)

        addArticleRequest.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                if (response.isSuccessful) {
                    var articleScanned : List<Article> = response.body()!!.data
                    // GET ARTICLE CART OR FIND ARTICLE
                    var articleIsPresent = MyApp.articleList!![0].listArticleCart.find {
                            articleCart ->
                        articleCart.article == articleScanned[0]
                    }
                } else {
                    Log.e("GET ARTICLE FAILED", response.errorBody()!!.string())
                }
            }
            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                Log.e("GET ARTICLE FAILED", "Error : $t")
            }
        })
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val action = intent.action
        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
            var rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_TAG)
            val tag = intent.getParcelableExtra<Parcelable>(NfcAdapter.EXTRA_TAG)
            Toast.makeText(this, "c arrivé ici o moins", Toast.LENGTH_LONG + 2).show()
//            Log.e(action, action)
        }
    }

}
