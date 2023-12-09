package com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit
import com.google.gson.annotations.SerializedName

data class PostResponse (
    val userId: Int,
    val id : Int,
    val title: String?,
    @SerializedName("body")
    val text: String?
)