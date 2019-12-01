package com.epitech.cashmanager.model.responseModel

import com.epitech.cashmanager.model.Article
import com.google.gson.annotations.SerializedName

class ArticleResponse(
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: List<Article>
)