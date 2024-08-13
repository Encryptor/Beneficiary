package com.example.empowerbeneficiariesapp.adapters

import android.app.AlertDialog
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.empowerbeneficiariesapp.R
import com.model.Beneficiary

class BeneficiaryAdapter(private val beneficiaries: List<Beneficiary>) :
    RecyclerView.Adapter<BeneficiaryAdapter.BeneficiaryViewHolder>() {

    inner class BeneficiaryViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeneficiaryViewHolder {
        // Create a linear layout to hold the views
        val layout = LinearLayout(parent.context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(32, 32, 32, 32)
        }

        // Add TextViews for firstName, lastName, beneType, and designation
        val firstNameView = TextView(parent.context)
        val lastNameView = TextView(parent.context)
        val beneTypeView = TextView(parent.context)
        val designationView = TextView(parent.context)

        // Add them to the layout
        layout.addView(firstNameView)
        layout.addView(lastNameView)
        layout.addView(beneTypeView)
        layout.addView(designationView)

        return BeneficiaryViewHolder(layout)
    }

    override fun onBindViewHolder(holder: BeneficiaryViewHolder, position: Int) {
        val beneficiary = beneficiaries[position]

        val designation = if (beneficiary.designationCode == "P") "Primary" else "Contingent"

        (holder.layout.getChildAt(0) as TextView).apply {
            text = "First Name: ${beneficiary.firstName}"
            textSize = 16f
        }

        (holder.layout.getChildAt(0) as TextView).text = "First Name: ${beneficiary.firstName}"
        (holder.layout.getChildAt(1) as TextView).text = "Last Name: ${beneficiary.lastName}"
        (holder.layout.getChildAt(2) as TextView).text = "Beneficiary Type: ${beneficiary.beneType}"
        (holder.layout.getChildAt(3) as TextView).text = "Designation: $designation"

        holder.layout.setOnClickListener {
            // Create an AlertDialog to show additional information
            val builder = AlertDialog.Builder(holder.layout.context)
            builder.setTitle("Beneficiary Details")

            val dialogLayout = LinearLayout(holder.layout.context).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(32, 32, 32, 32)
            }

            val ssnView = TextView(holder.layout.context).apply {
                text = "SSN: ${beneficiary.socialSecurityNumber}"
                textSize = 16f
            }
            val dobView = TextView(holder.layout.context).apply {
                text = "Date of Birth: ${formatDate(beneficiary.dateOfBirth)}"
                textSize = 16f
            }
            val phoneView = TextView(holder.layout.context).apply {
                text = "Phone: ${beneficiary.phoneNumber}"
                textSize = 16f
            }
            val addressView = TextView(holder.layout.context).apply {
                text = "Address: ${beneficiary.beneficiaryAddress.firstLineMailing}, " +
                        "${beneficiary.beneficiaryAddress.city}, " +
                        "${beneficiary.beneficiaryAddress.stateCode} " +
                        "${beneficiary.beneficiaryAddress.zipCode}, " +
                        "${beneficiary.beneficiaryAddress.country}"

                textSize = 16f
            }

            dialogLayout.addView(ssnView)
            dialogLayout.addView(dobView)
            dialogLayout.addView(phoneView)
            dialogLayout.addView(addressView)

            builder.setView(dialogLayout)
            builder.setPositiveButton("OK", null)
            builder.show()
        }
    }

    override fun getItemCount(): Int = beneficiaries.size

    private fun formatDate(date: String): String {
        val month = date.substring(0, 2)
        val day = date.substring(2, 4)
        val year = date.substring(4, 8)
        return "$month/$day/$year"
    }
}
