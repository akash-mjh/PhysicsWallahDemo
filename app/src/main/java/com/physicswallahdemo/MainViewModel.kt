package com.physicswallahdemo

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Timer
import kotlin.concurrent.timerTask

class MainViewModel : ViewModel() {

    private val _currentDateTime = MutableLiveData<String>()
    val currentDateTime: LiveData<String>
        get() = _currentDateTime

    private val _currentDayofWeek = MutableLiveData<String>()
    val currentDayofWeek: LiveData<String>
        get() = _currentDayofWeek

    private val handler = Handler()
    private lateinit var runnable: Runnable

    private val _currentQuote = MutableLiveData<Quote>()
    val currentQuote: LiveData<Quote> get() = _currentQuote

    private val quotes = arrayListOf(
        Quote("Quote 1"),
        Quote("Quote 2"),
        Quote("Quote 3"),
        Quote("Quote 4"),
        Quote("Quote 5")
    )

    init {
        updateCurrentDateTime()
    }

    private fun updateCurrentDateTime() {
        val sdf = SimpleDateFormat("HH:mm:ss a \n EE, dd MMM", Locale.getDefault())
        val currentDateTimeString = sdf.format(Date())
        _currentDateTime.value = currentDateTimeString

        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val currentDayofWeek = dayFormat.format(Date())
        _currentDayofWeek.value = currentDayofWeek

        // Schedule the next update after 1 second
        runnable = Runnable { updateCurrentDateTime() }
        handler.postDelayed(runnable, 1000)
    }

    private fun updateQuote() {
        _currentQuote.value = quotes.random()
    }

    private fun startHourlyQuoteUpdate() {
        val timer = Timer()
        timer.scheduleAtFixedRate(timerTask { updateQuote() }, 0, 3600000)
    }


    override fun onCleared() {
        // Remove the scheduled updates when the ViewModel is cleared
        handler.removeCallbacks(runnable)
        super.onCleared()
    }
}