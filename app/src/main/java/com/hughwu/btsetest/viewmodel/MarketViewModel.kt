package com.hughwu.btsetest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.hughwu.btsetest.model.CoinIndexWSResponse
import com.hughwu.btsetest.model.Data
import com.hughwu.btsetest.model.DisplayData
import com.hughwu.btsetest.model.WSDataItem
import com.hughwu.btsetest.repo.MarketRepository
import com.hughwu.btsetest.repo.MarketRepositoryInterface
import com.hughwu.btsetest.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(private val repository: MarketRepositoryInterface) :ViewModel() {
    private lateinit var client: OkHttpClient
    private lateinit var socket: WebSocket

    private val wsReturn = MutableLiveData<List<WSDataItem>>()
    private val apiReturn = MutableLiveData<List<Data?>?>()

    val liveDataMerger = MediatorLiveData<List<DisplayData>>()

    init {
        val displayDataList = mutableListOf<DisplayData>()
        liveDataMerger.addSource(apiReturn, Observer {
            if (it != null) {
                for(data in it){
                    if (data != null) {
                        displayDataList.add(DisplayData(data.future, data.symbol, null))
                    }
                }
                liveDataMerger.value = displayDataList
            }
        })

        liveDataMerger.addSource(wsReturn, Observer {
            for(wsData in it){
                for(i in displayDataList.indices){
                    if(wsData.name == displayDataList[i].symbol){
                        displayDataList[i].price = wsData.price
                        break
                    }
                }
            }
            liveDataMerger.value = displayDataList
        })
    }

    fun getMarketListAPI(){
        viewModelScope.launch {
            val response = repository.getMarketList()
            apiReturn.value = response.data?.data?.sortedBy { it?.symbol }
        }
    }
    override fun onCleared() {
        super.onCleared()
        detachWebSocket()
    }
    fun attachWebSocket(){
        client = OkHttpClient()
        val request = Request.Builder().url(Util.WEBSOCKET_URL).build()
        val listener = FuturesSocketListener()
        socket = client.newWebSocket(request, listener)
    }

    fun detachWebSocket(){
        socket.send("{\"op\":\"unsubscribe\", \"args\":[\"coinIndex\"]}")
        socket.close(1000, "Leaving page now")
    }

    inner class FuturesSocketListener: WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            output("Open Connection")
            webSocket.send("{\"op\":\"subscribe\", \"args\":[\"coinIndex\"]}")
            output("$response")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            output("Received : $text")
            try{
                val coinIndex = Gson().fromJson(text, CoinIndexWSResponse::class.java)
                if(coinIndex.topic == "coinIndex") {
                    val targets = coinIndex.data.values.filter { it.type == 1 }
                    wsReturn.postValue(targets)
//                    output(Gson().toJson(targets))
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            webSocket.close(1000, null)
            output("Closing : $code / $reason")
        }


        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            output("Error : " + t.message)
        }

        private fun output(text: String?) {
            Log.d("Socket", text!!)
//            if(text.length > 1000){
//                output(text.substring(1000))
//            }
        }
    }
}