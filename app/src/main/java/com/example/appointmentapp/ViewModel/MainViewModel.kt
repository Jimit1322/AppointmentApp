package com.example.appointmentapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appointmentapp.Domain.CategoryModel
import com.example.appointmentapp.Domain.DoctorModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.sql.Ref

class MainViewModel(): ViewModel() {
    private val firebaseDatabase= FirebaseDatabase.getInstance()

//    _Category is like a kitchen (where data is cooked/updated).
//
//    category is like the dining area (others can see what's served, but can't cook or change it).


    private val _Category= MutableLiveData<MutableList<CategoryModel>>()
    private val _Doctors= MutableLiveData<MutableList<DoctorModel>>()


    val category:LiveData<MutableList<CategoryModel>> =_Category
    val doctor:LiveData<MutableList<DoctorModel>> =_Doctors

    fun loadCategory(){
        val ref=firebaseDatabase.getReference("Category")
//        That means your app will **listen for any changes** in the Category data.
        ref.addValueEventListener(object : ValueEventListener{
// This method is called automatically whenever the data at the "Category" node changes.
//snapshot holds the data from Firebase at that moment.
            override fun onDataChange(snapshot: DataSnapshot) {
              val lists=mutableListOf<CategoryModel>()
                for(childsnapshot in snapshot.children){
                    val list =childsnapshot.getValue(CategoryModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _Category.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadDoctors(){
        val Ref=firebaseDatabase.getReference("Doctors")
        Ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val lists=mutableListOf<DoctorModel>()
                    for(childsnapshot in snapshot.children){
                        val list=childsnapshot.getValue(DoctorModel ::class.java)
                        if(list!=null){
                            lists.add(list)
                        }
                    }
                _Doctors.value=lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }




}