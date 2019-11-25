package com.epitech.cashmanager.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.epitech.cashmanager.R
import com.epitech.cashmanager.databinding.ActivitySettingsBinding
import com.epitech.cashmanager.global.MyApp
import com.epitech.cashmanager.global.MyApp.GlobalVar
import com.epitech.cashmanager.model.BackObject
import com.epitech.cashmanager.model.PaymentSetting
import com.epitech.cashmanager.network.RetrofitInstance
import com.epitech.cashmanager.network.ServerConnection
import com.epitech.cashmanager.service.SettingService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL


class SettingsActivity : AppCompatActivity() {

    var backResponse: BackObject? = null
    lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        binding.signalState = MyApp.networkLink
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

    fun establishConnexion(view: View) {
        val serverIP = (findViewById<View>(R.id.serverIP) as TextInputEditText).text.toString()
        if (MyApp.hasNetworkAccess) {
            MyApp.backUrl = serverIP
            MyApp.backHostUrl = URL(MyApp.backUrl).host
            MyApp.networkInstance = RetrofitInstance()
            ServerConnection().execute()
        } else {
            Toast.makeText(applicationContext, "CHECK UR CONNEXION", Toast.LENGTH_LONG).show()
        }
    }

    fun getPaymentSettings() {
        val service = GlobalVar.networkInstance!!.retrofit.create(SettingService::class.java)
        val settingRequest: Call<BackObject> = service.getPaymentSetting()

        settingRequest.enqueue(object : Callback<BackObject> {
            override fun onResponse(call: Call<BackObject>, response: Response<BackObject>) {
                if (response.body() != null) {
                    backResponse = response.body()
                    Toast.makeText(applicationContext, backResponse!!.message, Toast.LENGTH_LONG).show()
                    GlobalVar.paymentSetting = backResponse!!.data as PaymentSetting
                }
            }
            override fun onFailure(call: Call<BackObject>, t: Throwable) {
                Log.e("COURSE", "Error : $t")
            }
        })
    }

//    fun getArticles() {
//        if (GlobalVar.articleList.isNotEmpty()) {
//            GlobalVar.networkLink.serverLink = true
//            for (c in GlobalVar.articleList)
//                Toast.makeText(applicationContext, "One course : ${c.title} : ${c.price} ", Toast.LENGTH_LONG).show()
//        }
//    }
}
