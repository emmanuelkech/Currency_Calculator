package com.emmanuelkech.currencycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var currencies = arrayOf("USD", "GBP", "EUR", "KES", "EGP", "KRW", "IDR")
    private var images = intArrayOf(R.drawable.usa, R.drawable.uk, R.drawable.greece, R.drawable.kenya, R.drawable.egypt, R.drawable.south_korea, R.drawable.south_korea)

    var baseCurrency = "EUR"
    var convertedCurrency = "USD"
    var conversionRate = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spin = findViewById<View>(R.id.spFromCurrency) as Spinner
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                currencies[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val spin2 = findViewById<View>(R.id.spToCurrency) as Spinner
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                currencies[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val customAdapter = SpinnerAdapter(applicationContext, images, currencies)
        spin.adapter = customAdapter
        spin2.adapter = customAdapter
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getApiResult() {

        if (etFrom != null && etFrom.text!!.isNotEmpty() && etFrom.text!!.isNotBlank()) {

            val API ="https://data.fixer.io/api/latest?access_key=6f59d835bf442ae808404d5a1835e99e&base=$baseCurrency&symbols=$convertedCurrency"

            if (baseCurrency == convertedCurrency){
                Toast.makeText(applicationContext, "You cannot convert same currency", Toast.LENGTH_SHORT).show()
            } else {
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        val apiResult = URL(API).readText()
                        val jsonObject = JSONObject(apiResult)

                        conversionRate = jsonObject.getJSONObject("rates").getString(convertedCurrency).toFloat()

                        Log.d("Main", "$conversionRate")
                        Log.d("Main", apiResult)

                        withContext(Dispatchers.Main){
                            val text = ((etFrom.text.toString().toFloat()) * conversionRate).toString()
                            etTo.setText(text)
                        }
                    } catch (e: Exception) {
                        Log.e("Main", "$e")
                    }
                }
            }
        }
    }
}