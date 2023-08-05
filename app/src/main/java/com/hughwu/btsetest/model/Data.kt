package com.hughwu.btsetest.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("active")
    val active: Boolean?,
    @SerializedName("availableQuotes")
    val availableQuotes: List<AvailableQuote?>?,
    @SerializedName("baseCurrency")
    val baseCurrency: String?,
    @SerializedName("closeTime")
    val closeTime: Long?,
    @SerializedName("display")
    val display: Boolean?,
    @SerializedName("displayOrder")
    val displayOrder: Int?,
    @SerializedName("displayQuote")
    val displayQuote: Any?,
    @SerializedName("favorite")
    val favorite: Boolean?,
    @SerializedName("fundingRate")
    val fundingRate: Double?,
    @SerializedName("future")
    val future: Boolean?,
    @SerializedName("globalDisplayQuote")
    val globalDisplayQuote: Any?,
    @SerializedName("inactiveTime")
    val inactiveTime: Long?,
    @SerializedName("initialMarginPercentage")
    val initialMarginPercentage: Double?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean?,
    @SerializedName("lastUpdate")
    val lastUpdate: Any?,
    @SerializedName("maintenanceMarginPercentage")
    val maintenanceMarginPercentage: Double?,
    @SerializedName("marketClosed")
    val marketClosed: Boolean?,
    @SerializedName("marketName")
    val marketName: String?,
    @SerializedName("matchingDisabled")
    val matchingDisabled: Boolean?,
    @SerializedName("openInterest")
    val openInterest: Double?,
    @SerializedName("openInterestUSD")
    val openInterestUSD: Double?,
    @SerializedName("openTime")
    val openTime: Long?,
    @SerializedName("prediction")
    val prediction: Boolean?,
    @SerializedName("quoteCurrency")
    val quoteCurrency: String?,
    @SerializedName("sortId")
    val sortId: Int?,
    @SerializedName("startMatching")
    val startMatching: Long?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("timeBasedContract")
    val timeBasedContract: Boolean?
)