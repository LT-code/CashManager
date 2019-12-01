package com.epitech.cashmanager.service

import com.epitech.cashmanager.model.responseModel.MachineSettingResponse
import com.epitech.cashmanager.model.requestModel.MachineSettingRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MachineService {
    @Headers("Content-Type: application/json")
    @POST("machine/connect")
    fun getConnexionToken(@Body body : MachineSettingRequest): Call<MachineSettingResponse>
}