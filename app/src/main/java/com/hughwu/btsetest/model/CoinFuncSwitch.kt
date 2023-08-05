package com.hughwu.btsetest.model


import com.google.gson.annotations.SerializedName

data class CoinFuncSwitch(
    @SerializedName("listedAsSpotQuote")
    val listedAsSpotQuote: Boolean?,
    @SerializedName("walletConvert")
    val walletConvert: Boolean?,
    @SerializedName("walletConvertFrom")
    val walletConvertFrom: Boolean?,
    @SerializedName("walletDeposit")
    val walletDeposit: Boolean?,
    @SerializedName("walletOtc")
    val walletOtc: Boolean?,
    @SerializedName("walletTransferToFutures")
    val walletTransferToFutures: Boolean?,
    @SerializedName("walletTransferToUser")
    val walletTransferToUser: Boolean?,
    @SerializedName("walletWithdraw")
    val walletWithdraw: Boolean?
)