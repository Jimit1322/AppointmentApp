package com.example.appointmentapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appointmentapp.Adaptor.CategoryAdaptor
import com.example.appointmentapp.Adaptor.TopDoctorAdaptor
import com.example.appointmentapp.R
import com.example.appointmentapp.ViewModel.MainViewModel
import com.example.appointmentapp.databinding.ActivityMainBinding
import com.google.firebase.database.core.view.View

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel= MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopDoctor()
    }

    private fun initTopDoctor() {
        binding.apply {
            progressBarTopDoctor.visibility=android.view.View.VISIBLE
            viewModel.doctor.observe(this@MainActivity, Observer{
                recyclerView.layoutManager= LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,false)
                recyclerView.adapter= TopDoctorAdaptor(it)
                progressBarTopDoctor.visibility= android.view.View.GONE


            })
        viewModel.loadDoctors()

            doctorlisttxt.setOnClickListener {
                startActivity(Intent(this@MainActivity, TopDoctorsActivity::class.java))
            }
        }
    }

    private fun initCategory() {
                binding.progreebarCategory.visibility=android.view.View.VISIBLE
                viewModel.category.observe(this, Observer{
                binding.viewCategory.layoutManager= LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,false)
                binding.viewCategory.adapter= CategoryAdaptor(it)
                binding.progreebarCategory.visibility=android.view.View.GONE

            })
        viewModel.loadCategory()
    }
}