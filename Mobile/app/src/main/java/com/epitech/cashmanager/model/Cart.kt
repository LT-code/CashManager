package com.epitech.cashmanager.model

import com.google.gson.annotations.SerializedName

class Cart (
    @SerializedName("totalBill")
    var total : Double,
    @SerializedName("listArticle")
    var listArticleCart : List<ArticleCart>
)