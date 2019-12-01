package com.epitech.cashmanager.model

import com.google.gson.annotations.SerializedName

class MachineSettingSession (
    @SerializedName("machine")
    var machine : Machine,
    @SerializedName("currentIdCart")
    var currentIdCart : Int
)
