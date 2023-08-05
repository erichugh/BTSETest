package com.hughwu.btsetest.model


import com.google.gson.annotations.SerializedName

data class WSDataItem(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("gains")
    val gains: Double?,
    @SerializedName("high")
    val high: Double?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("low")
    val low: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("open")
    val `open`: Double?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("volume")
    val volume: Double?
)