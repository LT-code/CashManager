package com.epitech.cashmanager.service

import retrofit2.http.GET
import retrofit2.Call
import com.epitech.cashmanager.model.Course


interface CoursesService {
    @GET("/courses")
    fun getCourses(): Call<List<Course>>
}