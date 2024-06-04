package com.example.productdetails.entity

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val languages: String,
    val sampleReports: String,
    val pages: Int,
    val pagesInText: String,
    val reportType: String,
    val authentic: String,
    val remedies: String,
    val vedic: String,
    val price: Int,
    val discount: Int,
    val appDiscount: Int,
    val couponDiscount: Int,
    val imagePathSquare: String,
    val imagePathWide: String,
    val soldCount: String,
    val avg: Double
)
