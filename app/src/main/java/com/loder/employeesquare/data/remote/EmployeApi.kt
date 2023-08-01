package com.loder.employeesquare.data.remote

import com.loder.employeesquare.data.model.Employee
import com.loder.employeesquare.data.model.EmployeeModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface EmployeApi {

    @GET()
    fun getEmployess(@Url url: String): Call<EmployeeModel>
}
