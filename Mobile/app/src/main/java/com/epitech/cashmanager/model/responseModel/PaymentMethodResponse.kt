package com.epitech.cashmanager.model.responseModel

import com.epitech.cashmanager.model.PaymentMethod
import com.google.gson.annotations.SerializedName

class PaymentMethodResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: List<PaymentMethod>

)