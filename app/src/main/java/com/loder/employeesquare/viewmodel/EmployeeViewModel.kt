package com.loder.employeesquare.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loder.employeesquare.data.model.EmployeeModel
import com.loder.employeesquare.data.remote.Repository
import com.loder.employeesquare.util.DispatcherProvider
import com.loder.employeesquare.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(

    private val repository: Repository,
    private val dispatcherProvider: DispatcherProvider,

) : ViewModel() {

    sealed class EmployeeStatus {
        class Success(val result: EmployeeModel) : EmployeeStatus()
        class Failure(val error: String) : EmployeeStatus()
        object Loading : EmployeeStatus()
        object Empty : EmployeeStatus()
    }

    private val _employee = MutableStateFlow<EmployeeStatus> (EmployeeStatus.Empty)
    val employeeStatus: StateFlow<EmployeeStatus> = _employee

    fun getEmployees() {
        viewModelScope.launch(dispatcherProvider.io) {
            _employee.value = EmployeeStatus.Loading

            when (
                val response = repository.getEmployee()
            ) {
                is Resource.Error -> _employee.value = EmployeeStatus.Failure(response.message!!)
                is Resource.Success -> _employee.value = EmployeeStatus.Success(response.data!!)
            }
        }
    }
}
