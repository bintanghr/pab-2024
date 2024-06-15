package com.example.calculatorandtimer.ui.timer

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class MainViewModel : ViewModel() {
    companion object {
        private const val ONE_SECOND = 1000L
    }

    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long>
        get() = _elapsedTime

    private var initialTime: Long = 0
    private var timer: Timer? = null
    private var pauseTime: Long = 0

    init {
        _elapsedTime.value = 0
    }

    fun startTimer() {
        if (timer == null) {
            initialTime = SystemClock.elapsedRealtime()
            timer = Timer()
            timer?.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    val elapsed = (SystemClock.elapsedRealtime() - initialTime + pauseTime) / 1000
                    _elapsedTime.postValue(elapsed)
                }
            }, ONE_SECOND, ONE_SECOND)
        }
    }

    fun pauseTimer() {
        timer?.cancel()
        timer = null
        pauseTime += SystemClock.elapsedRealtime() - initialTime
    }

    fun resumeTimer() {
        if (timer == null) {
            initialTime = SystemClock.elapsedRealtime()
            timer = Timer()
            timer?.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    val elapsed = (SystemClock.elapsedRealtime() - initialTime + pauseTime) / 1000
                    _elapsedTime.postValue(elapsed)
                }
            }, ONE_SECOND, ONE_SECOND)
        }
    }

    fun stopTimer() {
        timer?.cancel()
        timer = null
        _elapsedTime.value = 0
        pauseTime = 0
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}
