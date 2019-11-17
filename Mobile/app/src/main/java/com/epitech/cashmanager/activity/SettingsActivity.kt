package com.epitech.cashmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.epitech.cashmanager.R
import com.epitech.cashmanager.global.MyApp.GlobalVar
import com.epitech.cashmanager.model.Course
import com.epitech.cashmanager.network.RetrofitInstance
import com.epitech.cashmanager.service.CoursesService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun testCo(view: View) {

        val service = RetrofitInstance().retrofit.create(CoursesService::class.java)
        val courseRequest: Call<List<Course>> = service.getCourses()

        courseRequest.enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                Toast.makeText(applicationContext, GlobalVar.globalVar, Toast.LENGTH_LONG).show()
                GlobalVar.coursesList.addAll(response.body()!!)
                if (GlobalVar.coursesList.isNotEmpty()) {
                    GlobalVar.serverLink = true
                    for (c in GlobalVar.coursesList)
                        Toast.makeText(applicationContext, "One course : ${c.title} : ${c.time} ", Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                Log.e("COURSE", "Error : $t")
            }
        })
    }
}
