package com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit
import com.example.myapplication.retrofit.APi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val instance: APi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(APi::class.java)
    }
}