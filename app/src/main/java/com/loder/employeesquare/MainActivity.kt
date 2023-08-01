package com.loder.employeesquare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.loder.employeesquare.adapter.EmployeeAdapter
import com.loder.employeesquare.databinding.ActivityMainBinding
import com.loder.employeesquare.viewmodel.EmployeeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: EmployeeViewModel
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var employeeRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        viewModel.getEmployess()
        val list = viewModel.observeEmployeLiveData().observe(
            this,
            Observer {
                // Log.e("TAGEMPLO", "aki")
                // println(it.employees)
                employeeRecycler = binding.rvEmployees
                employeeAdapter = EmployeeAdapter(it.employees)
                employeeRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
                employeeRecycler.setHasFixedSize(true)
                employeeRecycler.adapter = employeeAdapter
            },
        )
    }
}
