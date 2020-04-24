package com.ashwanisng.covid19_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_list.view.*

class CoronaAdapter (val list: List<StatewiseItem>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

//        in listView it make view continuously on scrolling down
//         So first we use by default view so that it's doesn't repeat it

//        it means if convertView is not null then assign it to view
//        if it's null then use the rightside of code
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)

//        fetch position of the item in the list
        val item = list[position]
//        now call the text
        view.cnfrmd_tv.text = SpanniableDelta("${item.confirmed}\n ↑ ${item.deltaconfirmed ?: "0"}",
            "#D32F2F",
            item.confirmed?.length ?: 0
            )

        view.active_tv.text = SpanniableDelta("${item.active}\n ↑ ${item.deltaactive ?: "0"}",
            "#1976D2",
            item.active?.length ?: 0
        )

        view.rcrvd_tv.text = SpanniableDelta("${item.recovered}\n ↑ ${item.deltarecovered ?: "0"}",
            "#388E3C",
            item.recovered?.length ?: 0
        )

        view.dcsd_tv.text = SpanniableDelta("${item.deaths}\n ↑ ${item.deltadeaths ?: "0"}",
            "#FBC02D",
            item.deaths?.length ?: 0
        )
//        view.active_tv.text = item.active
////        view.rcrvd_tv.text = item.recovered
//        view.dcsd_tv.text = item.deaths


        view.state_tv.text = item.state



        return view
    }

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = list.size
}