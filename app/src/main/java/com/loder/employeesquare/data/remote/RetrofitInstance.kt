package com.loder.employeesquare.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: EmployeApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeApi::class.java)
    }
}
