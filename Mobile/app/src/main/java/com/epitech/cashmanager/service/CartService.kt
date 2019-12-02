package com.epitech.cashmanager.service

import com.epitech.cashmanager.model.requestModel.CartArticleRequest
import com.epitech.cashmanager.model.responseModel.ArticleResponse
import com.epitech.cashmanager.model.responseModel.CartResponse
import retrofit2.Call
import retrofit2.http.*

interface CartService {
    @GET("cart/get_articles")
    fun getCartArticles(@Header("Authorization") authorization : String,
                      @Query("idCart") idCart : Int): Call<CartResponse>

    @Headers("Content-Type: application/json")
    @POST("cart/add_article")
    fun addOneArticle(@Header("Authorization") authorization : String,
                      @Body body : CartArticleRequest): Call<ArticleResponse>
}