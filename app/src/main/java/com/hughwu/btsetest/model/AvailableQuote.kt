package com.hughwu.btsetest.model


import com.google.gson.annotations.SerializedName

data class AvailableQuote(
    @SerializedName("coinFuncSwitch")
    val coinFuncSwitch: CoinFuncSwitch?,
    @SerializedName("crypto")
    val crypto: Boolean?,
    @SerializedName("currencyUnitMultiplier")
    val currencyUnitMultiplier: Any?,
    @SerializedName("decimals")
    val decimals: Int?,
    @SerializedName("depositMin")
    val depositMin: Double?,
    @SerializedName("fiat")
    val fiat: Boolean?,
    @SerializedName("gmtCreate")
    val gmtCreate: Long?,
    @SerializedName("gmtModified")
    val gmtModified: Long?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("increment")
    val increment: Double?,
    @SerializedName("isDefault")
    val isDefault: Int?,
    @SerializedName("isQuote")
    val isQuote: Boolean?,
    @SerializedName("isSettlement")
    val isSettlement: Int?,
    @SerializedName("isStable")
    val isStable: Boolean?,
    @SerializedName("isSupportAddressExtension")
    val isSupportAddressExtension: Boolean?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("maxSize")
    val maxSize: Double?,
    @SerializedName("minSize")
    val minSize: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("shortName")
    val shortName: String?,
    @SerializedName("sortId")
    val sortId: Int?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("typeEnum")
    val typeEnum: String?
)