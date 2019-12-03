package com.epitech.cashmanager.model

import com.google.gson.annotations.SerializedName

class PaymentMethod (
    @SerializedName("idCart")
    var idCart : Int,
    @SerializedName("idType")
    var idType : Int,
    @SerializedName("numberAttempts")
    var numberAttempts : Int,
    @SerializedName("status")
    var status : String
)