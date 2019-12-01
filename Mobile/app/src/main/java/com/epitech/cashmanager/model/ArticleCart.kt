package com.epitech.cashmanager.model

import com.google.gson.annotations.SerializedName

class ArticleCart (
    @SerializedName("quantity")
    var quantity: Int,
    @SerializedName("article")
    var article: Article
)