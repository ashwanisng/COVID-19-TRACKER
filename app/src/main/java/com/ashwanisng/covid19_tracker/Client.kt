package com.ashwanisng.covid19_tracker

import okhttp3.OkHttpClient
import okhttp3.Request

object Client{
//    by which we can do networking
    private val okHttpClient = OkHttpClient()

//request url to fetch data
    private val request = Request.Builder().
    url("https://api.covid19india.org/data.json").build()

//    now by this we call this
    val api = okHttpClient.newCall(request)
}