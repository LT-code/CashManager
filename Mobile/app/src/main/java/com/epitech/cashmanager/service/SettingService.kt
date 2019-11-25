package com.epitech.cashmanager.service

import com.epitech.cashmanager.model.BackObject
import retrofit2.Call
import retrofit2.http.POST

interface SettingService {
    @POST("setting/create")
    fun getPaymentSetting(): Call<BackObject>
}
