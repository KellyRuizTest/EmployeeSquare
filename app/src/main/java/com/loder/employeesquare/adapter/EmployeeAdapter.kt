package com.loder.employeesquare.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loder.employeesquare.EmployeeInfoActivity
import com.loder.employeesquare.R
import com.loder.employeesquare.data.model.Employee
import com.loder.employeesquare.databinding.EmployeeLayoutBinding
import com.squareup.picasso.Picasso

class EmployeeAdapter(private val employeeList: List<Employee>) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EmployeeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(employeeList.get(position).photoUrlSmall)
            .placeholder(R.drawable.user_placeholder)
            .resize(300, 300).onlyScaleDown()
            .into(holder.binding.showUserImage)

        holder.binding.textName.setText(employeeList.get(position).fullName)
        holder.binding.textTeam.setText(employeeList.get(position).team)

        val employee = employeeList.get(position)

        holder.binding.cardviewEmployee.setOnClickListener {
            val intent = Intent(it.context, EmployeeInfoActivity::class.java).also {
                it.putExtra("EmployeeDetail", employee)
            }
            it.context.startActivity(intent)
        }
    }

    inner class ViewHolder(val binding: EmployeeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}
