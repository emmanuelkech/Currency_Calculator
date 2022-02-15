package com.emmanuelkech.currencycalculator

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.google.android.material.textview.MaterialTextView

class SpinnerAdapter(context: Context, private var images: IntArray, private var currencies: Array<String>) : BaseAdapter() {
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflater.inflate(R.layout.custom_spinner_items,null)
        val icon = view.findViewById<View>(R.id.curImg) as ImageView
        val names = view.findViewById<View>(R.id.curTxt) as MaterialTextView
        icon.setImageResource(images[p0])
        names.text = currencies[p0]

        return view
    }

}

