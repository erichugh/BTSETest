package com.hughwu.btsetest.model


import com.google.gson.annotations.SerializedName

data class MarketResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("msg")
    val msg: String?,
    @SerializedName("success")
    val success: Boolean?,
    @SerializedName("time")
    val time: Long?
)