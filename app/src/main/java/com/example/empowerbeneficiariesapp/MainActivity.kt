package com.example.empowerbeneficiariesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.empowerbeneficiariesapp.adapters.BeneficiaryAdapter
import com.example.empowerbeneficiariesapp.viewmodel.BeneficiaryViewModel
import com.model.Address
import com.model.Beneficiary

class MainActivity: AppCompatActivity() {

    private lateinit var beneficiaryViewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beneficiaryViewModel = ViewModelProvider(this).get(BeneficiaryViewModel::class.java)

        (beneficiaryViewModel as BeneficiaryViewModel).beneficiaries.observe(this, Observer { beneficiaries ->
            // Create a RecyclerView programmatically
            val recyclerView = RecyclerView(this).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            recyclerView.adapter = BeneficiaryAdapter(beneficiaries)
            // Set the RecyclerView as the content view of the activity
            setContentView(recyclerView)
        })
    }
}
