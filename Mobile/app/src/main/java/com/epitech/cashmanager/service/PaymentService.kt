package com.epitech.cashmanager.service

import com.epitech.cashmanager.model.requestModel.PayRequest
import retrofit2.Call
import com.epitech.cashmanager.model.requestModel.PaymentMethodRequest
import com.epitech.cashmanager.model.responseModel.PaymentMethodResponse
import retrofit2.http.*

interface PaymentService {
    @Headers("Content-Type: application/json")
    @POST("payment/choose_payment_type")
    fun choosePaymentMethod(@Header("Authorization") authorization : String,
                            @Body body : PaymentMethodRequest) : Call<PaymentMethodResponse>

    @Headers("Content-Type: application/json")
    @PUT("payment/pay")
    fun payWithBankCheck(@Header("Authorization") authorization : String,
                            @Body body : PayRequest) : Call<PaymentMethodResponse>
}