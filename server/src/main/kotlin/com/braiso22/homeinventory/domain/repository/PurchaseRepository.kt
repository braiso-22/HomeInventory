package com.braiso22.homeinventory.domain.repository

import com.braiso22.homeinventory.domain.model.Purchase
import com.braiso22.homeinventory.domain.model.PurchaseId

interface PurchaseRepository {
    suspend fun findById(id: PurchaseId): Purchase?
    suspend fun findAll(): List<Purchase>
    suspend fun save(purchase: Purchase): Purchase
}
