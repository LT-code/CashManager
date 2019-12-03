package com.epitech.cashmanager.global

import android.app.Application
import android.nfc.NfcAdapter
import com.epitech.cashmanager.model.*
import com.epitech.cashmanager.network.RetrofitInstance
import com.epitech.cashmanager.network.ServerConnection
import com.epitech.cashmanager.network.NetworkLink

class MyApp : Application() {
    companion object GlobalVar {

        // Device
        lateinit var deviceId : String

        // Network
        var backUrl = ""
        var backHostUrl = ""
        var networkInstance : RetrofitInstance? = null
        var networkLink = NetworkLink(false)
        var hasNetworkAccess = false
        var machineSettingSession : List<MachineSettingSession>? = null

        fun refreshConnexionState() {
            if (!hasNetworkAccess || backHostUrl.isNullOrEmpty()) networkLink.serverLink = false
            if (hasNetworkAccess && !networkLink.serverLink && !backHostUrl.isNullOrEmpty()) {
                ServerConnection().execute()
            }
        }

        // Article
        var articleList : List<Cart>? = null

        fun hasNotEmptyCart() : Boolean {
            return (!articleList.isNullOrEmpty() &&
                    !articleList!![0].listArticleCart.isNullOrEmpty())
        }

        // Payment
        var paymentMethodResponse : List<PaymentMethod>? = null
        var actualBankCheckScanned : String? = null

        // NFC
        var isCreditCardSelected: Boolean = false
        var isBankCheckSelected: Boolean = false

        lateinit var nfcAdapter: NfcAdapter

        var nfcReader : NdefReaderTask = NdefReaderTask()

        fun isNFCCompatible(nfcAdapter: NfcAdapter?): Boolean {
            return (nfcAdapter != null)
        }

        fun isNFCActivate(nfcAdapter: NfcAdapter?): Boolean {
            return if (isNFCCompatible(nfcAdapter!!)) {
                (nfcAdapter.isEnabled)
            } else {
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}