package com.loder.employeesquare

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loder.employeesquare.adapter.EmployeeAdapter
import com.loder.employeesquare.databinding.ActivityMainBinding
import com.loder.employeesquare.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var employeeRecycler: RecyclerView

    private val viewModel: EmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getEmployees()
        employeeRecycler = binding.rvEmployees

        lifecycleScope.launchWhenStarted {
            viewModel.employeeStatus.collect { event ->
                when (event) {
                    is EmployeeViewModel.EmployeeStatus.Success -> {
                        println(event.result.employees)
                        employeeAdapter = EmployeeAdapter(event.result.employees)
                        employeeRecycler.apply {
                            adapter = employeeAdapter
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            setHasFixedSize(true)
                        }
                    }
                    is EmployeeViewModel.EmployeeStatus.Failure -> {
                        Toast.makeText(this@MainActivity, event.error, Toast.LENGTH_LONG).show()
                    }
                    is EmployeeViewModel.EmployeeStatus.Loading -> {
                        Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }
}
