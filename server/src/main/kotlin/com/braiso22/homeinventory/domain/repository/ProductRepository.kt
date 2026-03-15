package com.braiso22.homeinventory.domain.repository

import com.braiso22.homeinventory.domain.model.Product
import com.braiso22.homeinventory.domain.model.ProductId

interface ProductRepository {
    suspend fun findById(id: ProductId): Product?
    suspend fun findAll(): List<Product>
    suspend fun save(product: Product): Product
    suspend fun delete(id: ProductId)
}
