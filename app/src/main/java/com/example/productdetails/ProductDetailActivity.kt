package com.example.productdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.productdetails.databinding.ProductDetailBinding
import com.example.productdetails.entity.ProductEntity

class ProductDetailActivity : AppCompatActivity(), SampleReportAdapter.OnReportClickListener {
    private val binding by lazy { ProductDetailBinding.inflate(layoutInflater) }

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        val productName = intent.getStringExtra("product_Name")
        binding.productName.text = productName


        productName?.let { name ->
            productViewModel.allProducts.observe(this) { products ->
                val product = products.find { it.name == name }
                product?.let {
                    setProductDetails(it)

                }
            }
        }


    }

    private fun setProductDetails(product: ProductEntity) {
        with(binding) {
            productName.text = product.name
            description.text = product.description
            availableLanguages.text = product.languages
            pages.text = product.pages.toString()
            pagesintext.text = product.pagesInText
            reportType.text = product.reportType
            authentic.text = product.authentic
            remedies.text = product.remedies
            vedic.text = product.vedic
            price.text = product.price.toString()
            discount.text = product.discount.toString()
            appDiscount.text = product.appDiscount.toString()
            couponDiscount.text = product.couponDiscount.toString()
            soldcount.text = product.soldCount
            avg.text = product.avg.toString()

            Glide.with(this@ProductDetailActivity)
                .load(product.imagePathSquare)
                .into(imageOne)

            Glide.with(this@ProductDetailActivity)
                .load(product.imagePathWide)
                .into(imageTwo)


         //   val sampleReports = listOf(product.sampleReports)

            val sampleReports = product.sampleReports.split(",").mapNotNull {
                val parts = it.split(":", limit = 2)
                println("sampleReports parts-->>>>>  $parts ")
                if (parts.size == 2) parts[0].trim() to parts[1].trim() else {
                    null
                }


            }
             println("sampleReports -->>>>>   $sampleReports")


            val adapter = SampleReportAdapter(sampleReports, this@ProductDetailActivity)
            sampleReportsRecyclerview.adapter = adapter
            adapter.setOnReportClickListener(this@ProductDetailActivity)
            sampleReportsRecyclerview.layoutManager =
                LinearLayoutManager(this@ProductDetailActivity)


        }
    }


    override fun onReportClicked(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}


