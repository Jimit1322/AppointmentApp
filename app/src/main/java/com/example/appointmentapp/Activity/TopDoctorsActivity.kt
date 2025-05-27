package com.example.appointmentapp.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appointmentapp.Activity.MainActivity
import com.example.appointmentapp.Adaptor.TopDoctorAdaptor
import com.example.appointmentapp.Adaptor.TopDoctorAdaptor2
import com.example.appointmentapp.R
import com.example.appointmentapp.ViewModel.MainViewModel
import com.example.appointmentapp.databinding.ActivityTopDoctorsBinding
import com.example.appointmentapp.databinding.ViewholderTopDoctorBinding

class TopDoctorsActivity : BaseActivity() {
    private lateinit var binding: ActivityTopDoctorsBinding
    private val viewModel= MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTopDoctorsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTopDoctor()
    }
    private fun initTopDoctor() {
        binding.apply {
            progressBarTopDoctor.visibility=android.view.View.VISIBLE
            viewModel.doctor.observe(this@TopDoctorsActivity, Observer{
                viewTopDoctor.layoutManager= LinearLayoutManager(this@TopDoctorsActivity,
                    LinearLayoutManager.VERTICAL,false)
                viewTopDoctor.adapter= TopDoctorAdaptor2(it)
                progressBarTopDoctor.visibility= android.view.View.GONE


            })
            viewModel.loadDoctors()

            backbtn.setOnClickListener {
                finish()
            }
        }
    }
}