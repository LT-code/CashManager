package com.epitech.cashmanager.global

import android.app.Application
import com.epitech.cashmanager.model.Course

class MyApp : Application() {
    companion object GlobalVar {
        const val backUrl = "http://mobile-courses-server.herokuapp.com"
        var globalVar = "I am Global Variable"
        var coursesList = mutableListOf<Course>()
        var serverLink = false
    }

    override fun onCreate() {
        super.onCreate()
    }
}