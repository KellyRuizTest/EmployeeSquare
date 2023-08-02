package com.loder.employeesquare.data.remote

import com.loder.employeesquare.data.model.EmployeeModel
import com.loder.employeesquare.util.Resource
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val api: EmployeApi) : MainRepository {
    override suspend fun getEmployee(): Resource<EmployeeModel> {
        return try {
            val response = api.getEmployess()
            val result = response.body()

            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message!!)
        }
    }
}
