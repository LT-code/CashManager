package com.epitech.cashmanager.model.requestModel

import com.google.gson.annotations.SerializedName

class PaymentMethodRequest (
    @SerializedName("idCart")
    var idCart : Int,
    @SerializedName("idType")
    var idType : Int
)