package com.epitech.cashmanager.model.responseModel

import com.epitech.cashmanager.model.Cart
import com.google.gson.annotations.SerializedName

class CartResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: List<Cart>
)