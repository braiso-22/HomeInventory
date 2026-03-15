package com.braiso22.homeinventory.domain.repository

import com.braiso22.homeinventory.domain.model.*

interface StockItemRepository {
    suspend fun findById(id: StockItemId): StockItem?
    suspend fun findByProductId(productId: ProductId): List<StockItem>
    suspend fun findByLocation(location: Location): List<StockItem>
    suspend fun findByStatus(status: StockStatus): List<StockItem>
    suspend fun save(stockItem: StockItem): StockItem
    suspend fun delete(id: StockItemId)
}
