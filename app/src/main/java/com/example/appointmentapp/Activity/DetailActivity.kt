package com.example.appointmentapp.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.appointmentapp.Domain.DoctorModel
import com.example.appointmentapp.R
import com.example.appointmentapp.databinding.ActivityDetailBinding
import com.example.appointmentapp.databinding.ActivityMainBinding
import androidx.core.net.toUri

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:DoctorModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()

    }

    private fun getBundle() {
        item=intent.getParcelableExtra("object")!!

        binding.apply {
            degreetxt.text=item.Name
            specialtxt.text=item.Special
            patientstxt.text=item.Patiens
            addresstxt.text=item.Adrress
            bioTxt.text=item.Biography
            experinecetxt.text=item.Expriense.toString()+"Years"
            ratingTxt.text="${item.Rating}"
            backbtn.setOnClickListener {
                finish()
            }
            website.setOnClickListener {
                val i= Intent(Intent.ACTION_VIEW)
                i.setData(item.Site.toUri())
                startActivity(i)
            }
            Message.setOnClickListener {
                val uri= "smsto: ${item.Mobile}".toUri()
                val intent= Intent(Intent.ACTION_SENDTO,uri)
                intent.putExtra("sms_body","The SMS Text")
                startActivity(intent)
            }
            call.setOnClickListener {
                val uri="Tel:"+ item.Mobile.trim()
                val intent=Intent(Intent.ACTION_DIAL,
                    uri.toUri())
                startActivity(intent)
            }
            Direction.setOnClickListener {
                val intent= Intent(Intent.ACTION_VIEW,Uri.parse(item.Location))
                startActivity(intent)
            }
            share.setOnClickListener {
                val intent= Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_SUBJECT,item.Name)
                intent.putExtra(Intent.EXTRA_TEXT,item.Name+" "+item.Adrress+" "+item.Mobile)
                startActivity(Intent.createChooser(intent,"Choose One"))

            }

            Glide.with(this@DetailActivity)
                .load(item.Picture)
                .into(img)



        }

    }
}