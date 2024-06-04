package com.example.productdetails

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productdetails.databinding.ActivityMainBinding
import com.example.productdetails.entity.ProductEntity
import com.example.productdetails.model.ProductsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity(),ProductListAdapter.ProductListener {

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}

    private lateinit var productViewModel: ProductViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }

        val adapter = ProductListAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.allProducts.observe(this) { products ->
            products?.let { adapter.setProducts(it) }
        }

    }

    override fun onProductClick(productEntity: ProductEntity) {

        val intent = Intent(this,ProductDetailActivity::class.java)
        intent.putExtra("product_Name",productEntity.name)
        startActivity(intent)
    }
}
interface ApiService {
    @GET("test/products.php")
    suspend fun getProducts(): ProductsResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://apps.clickastro.com/"

    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
