package com.epitech.cashmanager.service

import com.epitech.cashmanager.model.Article
import retrofit2.http.GET
import retrofit2.Call

interface ArticleService {
    @GET("/articles")
    fun getArticles(): Call<List<Article>>
}
