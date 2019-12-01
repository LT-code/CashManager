package com.epitech.cashmanager.network

import android.os.AsyncTask
import android.util.Log
import com.epitech.cashmanager.global.MyApp
import java.net.InetAddress


class ServerConnection : AsyncTask<String, Int, Boolean>() {
    override fun doInBackground(vararg params: String?): Boolean? {
        try {
            var ipAddr = InetAddress.getByName(MyApp.backHostUrl)
            if (ipAddr != null && !ipAddr.equals("")) {
                return true
            } else {
                Log.d("URL DOWN", "URL DOWN")
                return false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        MyApp.networkLink.serverLink = (result!! && MyApp.machineSettingSession != null
                && !MyApp.machineSettingSession!![0].machine.token.isNullOrEmpty())
        Log.d("serverLink", MyApp.networkLink.serverLink.toString())
    }
}