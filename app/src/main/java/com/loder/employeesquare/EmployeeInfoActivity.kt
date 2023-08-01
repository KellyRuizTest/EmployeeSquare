package com.loder.employeesquare

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.loder.employeesquare.databinding.ActivityEmployeeInfoBinding
import com.squareup.picasso.Picasso

class EmployeeInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_info)

        val extras: Bundle? = intent.extras
        if (extras != null) {
            val name = findViewById<TextView>(R.id.name_detail).setText(extras.getString("name"))
            val email = findViewById<TextView>(R.id.email_detail).setText(extras.getString("email"))
            val phone = findViewById<TextView>(R.id.phone_detail).setText(extras.getString("phone"))
            val bio = findViewById<TextView>(R.id.bio_detail).setText(extras.getString("bio"))
            val pid = findViewById<TextView>(R.id.pid_detail).setText(extras.getString("pid"))
            val team = findViewById<TextView>(R.id.team_detail).setText(extras.getString("team"))
            val type = findViewById<TextView>(R.id.type_detail).setText(extras.getString("type"))

            val urlImage = extras.getString("url_image_l")
            val imageProfile : ImageView = findViewById<ImageView>(R.id.image_detail)
            Picasso.get().load(urlImage)
                .placeholder(R.drawable.user_placeholder)
                .resize(300, 300).onlyScaleDown()
                .into(imageProfile)
        }
    }
}
