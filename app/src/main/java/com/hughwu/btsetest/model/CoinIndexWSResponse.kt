package com.hughwu.btsetest.model

import com.google.gson.annotations.SerializedName

data class CoinIndexWSResponse(
    @SerializedName("topic")
    val topic: String,
    @SerializedName("id")
    val id: String?,
    @SerializedName("data")
    val data: Map<String, WSDataItem>
)
