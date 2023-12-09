package com.example.tugasandroidadvanced.Fragment.Retrofit.retrofit
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasandroidadvanced.R

class PostAdapter(private val list: ArrayList<PostResponse>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvText: TextView = itemView.findViewById(R.id.tvText)

        fun bind(postResponse: PostResponse) {
            val text = "user ID: ${postResponse.userId}\n"+
                    "Id: ${postResponse.id}\n" +
                    "Title: ${postResponse.title}\n " +
                    "Text: ${postResponse.text}"
            tvText.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }
}