package com.epitech.cashmanager.model.requestModel

import com.google.gson.annotations.SerializedName

class CartArticleRequest (
    @SerializedName("idCart")
    var idCart : Int,
    @SerializedName("codeArticle")
    var codeArticle : String
)