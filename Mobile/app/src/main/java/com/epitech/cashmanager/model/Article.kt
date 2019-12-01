package com.epitech.cashmanager.model

import com.google.gson.annotations.SerializedName

class Article(
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: Double,
    @SerializedName("code")
    var code: String
)
