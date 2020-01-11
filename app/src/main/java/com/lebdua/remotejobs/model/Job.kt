package com.lebdua.remotejobs.model

import com.google.gson.annotations.SerializedName

data class Job(
    @SerializedName("position") var position: String = ""
)