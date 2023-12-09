package com.example.tugasandroidadvanced.Fragment.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasandroidadvanced.R

class PekerjaanAdapter(private var mList: List<PekerjaanData>) :
    RecyclerView.Adapter<PekerjaanAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.textPekerjaan)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pekerjaan, parent , false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PekerjaanAdapter.LanguageViewHolder, position: Int) {
        holder.title.text = mList[position].title

    }

    override fun getItemCount(): Int {
    return  mList.size
    }

}