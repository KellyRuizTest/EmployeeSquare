package com.loder.employeesquare.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loder.employeesquare.data.model.EmployeeModel
import com.loder.employeesquare.data.remote.RetrofitInstance
import com.loder.employeesquare.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeeViewModel : ViewModel() {

    private var employeeLiveData = MutableLiveData<EmployeeModel>()

    fun getEmployess() {
        RetrofitInstance.api.getEmployess(Constants.BASE_URL).enqueue(object : Callback<EmployeeModel> {
            override fun onResponse(call: Call<EmployeeModel>, response: Response<EmployeeModel>) {
                if (response.body() != null) {
                    employeeLiveData.value = response.body()

                } else {
                   println(response.message())
                }
            }

            override fun onFailure(call: Call<EmployeeModel>, t: Throwable) {
                Log.e("Retrofit", t.message.toString())
                println(t.cause)
            }
        })
    }

    fun observeEmployeLiveData(): LiveData<EmployeeModel> {
        return employeeLiveData
    }
}
