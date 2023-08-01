package com.loder.employeesquare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.loder.employeesquare.data.model.Employee
import com.loder.employeesquare.databinding.ActivityEmployeeInfoBinding
import com.squareup.picasso.Picasso

class EmployeeInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val employee = intent.getSerializableExtra("EmployeeDetail") as Employee
        setData(employee)
    }

    private fun setData(employee: Employee) {
        binding.itemImage.nameDetail.text = employee.fullName
        binding.itemImage.pidDetail.text = employee.uuid.slice(0..7)
        binding.itemImage.teamDetail.text = employee.team
        binding.itemImage.typeDetail.text = employee.employeeType
        binding.itemBio.bioDetail.text = employee.biography
        binding.itemEmail.emailDetail.text = employee.emailAddress
        binding.itemPhone.phoneDetail.text = employee.phoneNumber
        Picasso.get().load(employee.photoUrlLarge)
            .placeholder(R.drawable.user_placeholder)
            .resize(300, 300).onlyScaleDown()
            .into(binding.itemImage.imageDetail)
    }
}
