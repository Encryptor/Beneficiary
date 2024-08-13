package com.example.empowerbeneficiariesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.empowerbeneficiariesapp.R
import com.model.Address
import com.model.Beneficiary
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

class BeneficiaryViewModel(application: Application) : AndroidViewModel(application) {

    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()
    val beneficiaries: LiveData<List<Beneficiary>> get() = _beneficiaries

    init {
        loadBeneficiaries()
    }

    private fun loadBeneficiaries() {
        val jsonString = readJsonFromRaw()
        val beneficiariesList = parseBeneficiariesJson(jsonString)
        _beneficiaries.postValue(beneficiariesList)
    }

    private fun readJsonFromRaw(): String {
        val inputStream = getApplication<Application>().resources.openRawResource(R.raw.beneficiaries)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = reader.readLine()
        }
        reader.close()
        return stringBuilder.toString()
    }

    private fun parseBeneficiariesJson(jsonString: String): List<Beneficiary> {
        val jsonArray = JSONArray(jsonString)
        val beneficiaries = mutableListOf<Beneficiary>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)

            val addressObject = jsonObject.getJSONObject("beneficiaryAddress")
            val address = Address(
                firstLineMailing = addressObject.getString("firstLineMailing"),
                scndLineMailing = addressObject.optString("scndLineMailing"),
                city = addressObject.getString("city"),
                zipCode = addressObject.getString("zipCode"),
                stateCode = addressObject.getString("stateCode"),
                country = addressObject.getString("country")
            )

            val beneficiary = Beneficiary(
                firstName = jsonObject.getString("firstName"),
                lastName = jsonObject.getString("lastName"),
                beneType = jsonObject.getString("beneType"),
                designationCode = jsonObject.getString("designationCode"),
                socialSecurityNumber = jsonObject.getString("socialSecurityNumber"),
                dateOfBirth = jsonObject.getString("dateOfBirth"),
                middleName = jsonObject.optString("middleName"),
                phoneNumber = jsonObject.getString("phoneNumber"),
                beneficiaryAddress = address
            )

            beneficiaries.add(beneficiary)
        }
        return beneficiaries
    }
}
