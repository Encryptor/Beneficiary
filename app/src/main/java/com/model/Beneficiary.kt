package com.model

data class Beneficiary(
    val firstName: String,
    val lastName: String,
    val beneType: String,
    val designationCode: String,
    val socialSecurityNumber: String,
    val dateOfBirth: String,
    val middleName: String?,
    val phoneNumber: String,
    val beneficiaryAddress: Address
)

