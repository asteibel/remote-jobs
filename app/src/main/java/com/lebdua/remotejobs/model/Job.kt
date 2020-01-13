package com.lebdua.remotejobs.model

import com.google.gson.annotations.SerializedName

data class Job(
    @SerializedName("position") var position: String = "",
    @SerializedName("date") var date: String = "",
    @SerializedName("company") var company: String = "",
    @SerializedName("company_logo") var companyLogo: String = "",
    @SerializedName("tags") var tags: List<String> = arrayListOf(),
    @SerializedName("logo") var _logo: String = "",
    @SerializedName("description") var description: String = "",
    @SerializedName("original") var original: Boolean = false,
    @SerializedName("verified") var verified: Boolean = false,
    @SerializedName("url") var url: String = ""
) {

    val logo: String
        get() = when {
            companyLogo.isNotBlank() -> companyLogo
            _logo.isNotBlank() -> _logo
            else -> ""
        }
}
