package com.epitech.cashmanager.network

import com.epitech.cashmanager.global.MyApp.GlobalVar.backUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {

    val retrofit = Retrofit.Builder()
        .baseUrl(backUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
