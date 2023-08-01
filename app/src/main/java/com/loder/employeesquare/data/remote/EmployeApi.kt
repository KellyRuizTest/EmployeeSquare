package com.loder.employeesquare.data.remote

import com.loder.employeesquare.data.model.EmployeeModel
import retrofit2.Response
import retrofit2.http.GET

interface EmployeApi {

    @GET("sq-mobile-interview/employees.json")
    suspend fun getEmployess(): Response<EmployeeModel>
}
