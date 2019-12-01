package com.epitech.cashmanager.service

import com.epitech.cashmanager.model.responseModel.MachineSettingResponse
import retrofit2.Call
import retrofit2.http.POST

interface SettingService {
    @POST("setting/create")
    fun getPaymentSetting(): Call<MachineSettingResponse>
}
