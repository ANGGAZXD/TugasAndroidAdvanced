package com.example.myapplication.retrofit
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.CommentResponse
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.CreatePostResponse
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.PostResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface APi {
    @GET("https://jsonplaceholder.typicode.com/posts")
    fun getPosts(
        @Query("userId") userId: Int,
        @Query("id") id: Int): Call<ArrayList<PostResponse>>

    @GET("post")
    fun getPosts(@QueryMap parameters: HashMap<String, String>): Call<ArrayList<PostResponse>>

    @GET
    fun getComment(@Url url: String): Call<ArrayList<CommentResponse>>

    @GET("posts/{id}/comments")
    fun getComment(@Path("id") postId: Int): Call<ArrayList<CommentResponse>>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String
    ): Call<CreatePostResponse>

    @FormUrlEncoded
    @PUT("posts/{id}")
    fun putPost(
        @Path("id")id: Int,
        @Field("userId") userId: Int,
        @Field("id")idField: Int,
        @Field("title") title: String?,
        @Field("body") text: String?
    ): Call<PostResponse>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun patchPost(
        @Path("id")id: Int,
        @Field("userId") userId: Int,
        @Field("id")idField: Int,
        @Field("title") title: String?,
        @Field("body") text: String?
    ): Call<PostResponse>

}

