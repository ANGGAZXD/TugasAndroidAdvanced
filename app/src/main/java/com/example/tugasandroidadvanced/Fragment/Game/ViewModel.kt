package com.example.tugasandroidadvanced.Fragment.Game

import androidx.lifecycle.ViewModel


class MyViewModel : ViewModel() {
    private var counter = 0

    fun getInitialCounter(): Int {
        return counter
    }

    fun getUpdateCounter(): Int {
        return ++counter
    }
}