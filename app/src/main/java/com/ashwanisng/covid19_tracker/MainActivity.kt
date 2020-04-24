package com.ashwanisng.covid19_tracker

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

//    now we have to make the object
    lateinit var coronaAdapter : CoronaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        when we open the app it show
        list_tv.addHeaderView(LayoutInflater.from(this).
        inflate(R.layout.item_header, list_tv, false))

        fetchResult()
    }

    private fun fetchResult() {
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO){Client.api.execute()}
            if (response.isSuccessful){
//                get the data from Json
                val data = Gson().fromJson(response.body?.string(), Response::class.java)
                launch(Dispatchers.Main) {
                    bindCombinedData(data.statewise[0])

//                    now we have to set the text
                    bindStateWiseData(data.statewise.subList(1, data.statewise.size))
                }
            }
        }
    }

    private fun bindStateWiseData(subList: List<StatewiseItem>) {

//        now we have to use the adapter
        coronaAdapter = CoronaAdapter(subList)
        list_tv.adapter = coronaAdapter

    }

    private fun bindCombinedData(data: StatewiseItem) {

//        now get the last updated time
        val lastUpdatedTime = data.lastupdatedtime
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        lastupdated_tv.text = "Last Updated\n ${getTimeAgo(simpleDateFormat.parse(lastUpdatedTime))}"

        confirmed_tv.text = data.confirmed
        active_tv.text = data.active
        recovered_tv.text = data.recovered
        deceased_tv.text = data.deaths
    }

//    now make a function to show the last updated time

    fun getTimeAgo(past : Date): String {

        val now = Date()

        val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - past.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - past.time)
        val hours = TimeUnit.MILLISECONDS.toHours(now.time - past.time)

        return when {
            seconds < 60 -> {
                "Few seconds ago"
            }

            minutes < 60 -> {
                "$minutes minutes ago"
            }

            hours < 24 -> {
                "$hours hours ago"
            }

            else -> {

                SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(past).toString()

            }


        }
    }
}
