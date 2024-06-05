package com.example.productdetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetails.databinding.SampleReportItemBinding
import com.example.productdetails.entity.ProductEntity

class SampleReportAdapter( private val sampleReports: List<Pair<String, String>>, private val context: Context) : RecyclerView.Adapter<SampleReportAdapter.ViewHolder>()  {

    private lateinit var binding: SampleReportItemBinding


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        println("sampleReport Adapter-->>>>>>> $sampleReports")
        binding = SampleReportItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return sampleReports.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val (language, url) = sampleReports[position]
        holder.titleFolder.text = "$language: $url "
       // val reportData = sampleReports[position].split(",") // Split by comma

      //  val url = reportData[1].substringAfter(":")

       // holder.titleFolder.text = reportData.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        }

    }
    interface OnReportClickListener {
        fun onReportClicked(url: String)
    }

    private var onReportClickListener: OnReportClickListener? = null

    fun setOnReportClickListener(listener: OnReportClickListener) {
        onReportClickListener = listener
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleFolder: TextView = itemView.findViewById(R.id.language)
    }

}

