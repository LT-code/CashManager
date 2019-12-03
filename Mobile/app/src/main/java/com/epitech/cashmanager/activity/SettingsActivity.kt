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
import com.epitech.cashmanager.model.responseModel.MachineSettingResponse
import com.epitech.cashmanager.model.requestModel.MachineSettingRequest
import com.epitech.cashmanager.model.responseModel.CartResponse
import com.epitech.cashmanager.network.RetrofitInstance
import com.epitech.cashmanager.service.CartService
import com.epitech.cashmanager.service.MachineService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL




class SettingsActivity : AppCompatActivity() {

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
        val serverPassword = (findViewById<View>(R.id.serverPassword) as TextInputEditText).text.toString()
        if (MyApp.hasNetworkAccess) {
            MyApp.backUrl = serverIP
            MyApp.backHostUrl = URL(MyApp.backUrl).host
            MyApp.networkInstance = RetrofitInstance()
            getToken(serverPassword)
        } else {
            Toast.makeText(applicationContext, "CHECK UR CONNEXION", Toast.LENGTH_LONG).show()
        }
    }

    fun getToken(serverPassword : String){
        val service = GlobalVar.networkInstance!!.retrofit.create(MachineService::class.java)
        var machineSettingRequest = MachineSettingRequest(MyApp.deviceId, serverPassword)
        val connectRequest: Call<MachineSettingResponse> = service.getConnexionToken(machineSettingRequest)

        connectRequest.enqueue(object : Callback<MachineSettingResponse> {
            override fun onResponse(call: Call<MachineSettingResponse>, response: Response<MachineSettingResponse>) {
                if (response.isSuccessful) {
                    GlobalVar.machineSettingSession = response.body()!!.data
                    MyApp.networkLink.serverLink = (!GlobalVar.machineSettingSession!![0].machine.token.isNullOrEmpty())
                    Toast.makeText(applicationContext, "LINK Success", Toast.LENGTH_LONG).show()
                    initShoppingCart()
                } else {
                    Log.e("CONNEXION FAILED", response.errorBody()!!.string())
                    Toast.makeText(applicationContext, "CONNEXION FAILED" + response.errorBody()!!.string(), Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<MachineSettingResponse>, t: Throwable) {
                Log.e("CONNEXION FAILED", "Error : $t")
                Toast.makeText(applicationContext, "CONNEXION FAILED - Technical Error", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun initShoppingCart() {
        val service = GlobalVar.networkInstance!!.retrofit.create(CartService::class.java)
        val connectRequest: Call<CartResponse> = service.getCartArticles(
            MyApp.machineSettingSession!![0].machine.token!!, MyApp.machineSettingSession!![0].currentIdCart)

        connectRequest.enqueue(object : Callback<CartResponse> {
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                if (response.isSuccessful) {
                    GlobalVar.articleList = response.body()!!.data
                    Toast.makeText(applicationContext, "Get Data OK :)", Toast.LENGTH_LONG).show()
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
}
