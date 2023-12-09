package com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit

data class CommentResponse(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
