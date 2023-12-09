package com.example.tugasandroidadvanced.Fragment.Retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.CommentAdapter
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.CommentResponse
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.CreatePostResponse
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.PostAdapter
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.PostResponse
import com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit.RetrofiClient
import com.example.tugasandroidadvanced.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : Fragment() {

    private val list = ArrayList<PostResponse>()
    private val listComment = ArrayList<CommentResponse>()
    private lateinit var tvResponseCode: TextView
    private lateinit var rvPost: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_retrofit, container, false)

        tvResponseCode = view.findViewById(R.id.tvResponseCode)
        rvPost = view.findViewById(R.id.rvPost)

        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(requireContext())

        //showPost()
        //createPost()
        //showComments()
        updatePosts()

        return view
    }

    private fun updatePosts() {
        RetrofiClient.instance.putPost(
            1,
            3,
            4,
            null,
            "Iya aku Badi yang sudah diubah"
        ).enqueue(object : Callback<PostResponse> {

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                tvResponseCode.text = response.code().toString()

                val responseText = "Response Code: ${response.code()}\n" +
                        "Title: ${response.body()?.title}\n" +
                        "Body: ${response.body()?.text} \n" +
                        "UserId: ${response.body()?.userId}\n" +
                        "Id: ${response.body()?.id}"
                tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun showComments() {
        val rvPost = view?.findViewById<RecyclerView>(R.id.rvPost)
        rvPost?.setHasFixedSize(true)
        rvPost?.layoutManager = LinearLayoutManager(requireContext())

        RetrofiClient.instance.getComment("posts/2/comments").enqueue(object : Callback<ArrayList<CommentResponse>> {
            override fun onResponse(
                call: Call<ArrayList<CommentResponse>>,
                response: Response<ArrayList<CommentResponse>>
            ) {
                tvResponseCode.text = response.body().toString()
                //Jika response.body() ada,maka perintah berikutnya dijalankan
                response.body()?.let { listComment.addAll(it) }
                val adapter = CommentAdapter(listComment)
                rvPost?.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<CommentResponse>>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun createPost() {
        RetrofiClient.instance.createPost(
            20,
            "Apa Atjah dah",
            "Gue Ganteng bet"
        ).enqueue(object : Callback<CreatePostResponse> {

            override fun onResponse(
                call: Call<CreatePostResponse>,
                response: Response<CreatePostResponse>
            ) {
                val responseText = "Response Code: ${response.code()}\n" +
                        "Title: ${response.body()?.title}\n" +
                        "Body: ${response.body()?.text} \n" +
                        "UserId: ${response.body()?.userId}\n" +
                        "Id: ${response.body()?.id}"
                tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {

            }

        })
    }

    private fun showPost() {
        val parameters = HashMap<String, String>()
        parameters["userId"] = "4"
        parameters["id"] = "32"

        RetrofiClient.instance.getPosts(parameters).enqueue(object : Callback<ArrayList<PostResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it) }
                val adapter = PostAdapter(list)
                rvPost?.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                // Handle failure here
            }
        })
    }

}
