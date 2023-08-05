package com.hughwu.btsetest.model

import com.google.gson.annotations.SerializedName

data class DisplayData(
    @SerializedName("future")
    val future: Boolean?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("price")
    var price: Double?
)
