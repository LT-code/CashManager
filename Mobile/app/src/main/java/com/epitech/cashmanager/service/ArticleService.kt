package com.epitech.cashmanager.service

import com.epitech.cashmanager.model.responseModel.ArticleResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query

interface ArticleService {
    @GET("article/get")
    fun getOneArticle(@Header("Authorization") authorization : String,
                      @Query("code") code : String): Call<ArticleResponse>
}
