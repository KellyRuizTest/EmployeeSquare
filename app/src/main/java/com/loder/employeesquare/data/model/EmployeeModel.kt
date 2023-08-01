package com.loder.employeesquare.data.model


import com.google.gson.annotations.SerializedName

data class EmployeeModel(
    @SerializedName("employees")
    val employees: List<Employee>
)