package com.loder.employeesquare.data.remote

import com.loder.employeesquare.data.model.EmployeeModel
import com.loder.employeesquare.util.Resource

interface MainRepository {

    suspend fun getEmployee(): Resource<EmployeeModel>
}
