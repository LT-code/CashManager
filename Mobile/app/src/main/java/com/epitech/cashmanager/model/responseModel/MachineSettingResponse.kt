package com.epitech.cashmanager.model.responseModel

import com.epitech.cashmanager.model.MachineSettingSession
import com.google.gson.annotations.SerializedName

class MachineSettingResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: List<MachineSettingSession>
)
