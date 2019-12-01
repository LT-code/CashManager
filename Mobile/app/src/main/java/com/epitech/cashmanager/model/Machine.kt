package com.epitech.cashmanager.model

import com.google.gson.annotations.SerializedName

class Machine (
    @SerializedName("idSetting")
    var idSetting : Int,
    @SerializedName("token")
    var token : String,
    @SerializedName("admin")
    var admin : Boolean,
    @SerializedName("id")
    var id : String
)
