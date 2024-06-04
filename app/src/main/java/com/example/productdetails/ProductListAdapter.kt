package com.example.productdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productdetails.databinding.ItemProductBinding
import com.example.productdetails.entity.ProductEntity
import com.google.gson.Gson

class ProductListAdapter(var productListener:ProductListener) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    private lateinit var binding:ItemProductBinding

    private var products = listOf<ProductEntity>()

    fun setProducts(products: List<ProductEntity>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val currentProduct = products[position]
        holder.titleFolder.text=currentProduct.name
        println("products -->>>> ${currentProduct.name}")

        holder.titleFolder.setOnClickListener{
            productListener.onProductClick(currentProduct)
        }



    }

    override fun getItemCount() = products.size

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleFolder: TextView = itemView.findViewById(R.id.productName)
    }
    interface ProductListener{
        fun onProductClick(noteEntity: ProductEntity)
    }
}
