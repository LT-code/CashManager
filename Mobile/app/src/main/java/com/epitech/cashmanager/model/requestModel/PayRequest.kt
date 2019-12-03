package com.epitech.cashmanager.model.requestModel

import com.google.gson.annotations.SerializedName

class PayRequest (
    @SerializedName("idCart")
    var idCart : Int,
    @SerializedName("code")
    var qrCode : String
)