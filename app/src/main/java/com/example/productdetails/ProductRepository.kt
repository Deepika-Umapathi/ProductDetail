package com.example.productdetails

import androidx.lifecycle.LiveData
import com.example.productdetails.dao.ProductDao
import com.example.productdetails.entity.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: LiveData<List<ProductEntity>> = productDao.getAllProducts()

    suspend fun refreshProducts() {
        withContext(Dispatchers.IO) {
            val response = RetrofitClient.apiService.getProducts()
            val productEntities = response.products.map { (key, value) ->
                ProductEntity(
                    id = key,
                    name = value.name,
                    description = value.description,
                    languages = value.availableLanguages.joinToString(","),
                    sampleReports = value.sampleReports.map { "${it.key}:${it.value}" }.joinToString(","),
                    pages = value.pages,
                    pagesInText = value.pagesintext,
                    reportType = value.report_type,
                    authentic = value.authentic,
                    remedies = value.remedies,
                    vedic = value.vedic,
                    price = value.price,
                    discount = value.discount,
                    appDiscount = value.appDiscount,
                    couponDiscount = value.couponDiscount,
                    imagePathSquare = value.imagePath.square,
                    imagePathWide = value.imagePath.wide,
                    soldCount = value.soldcount,
                    avg = value.avg
                )
            }
            productDao.insertAll(productEntities)
        }
    }
}

