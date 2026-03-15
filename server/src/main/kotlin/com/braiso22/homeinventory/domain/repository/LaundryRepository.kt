package com.braiso22.homeinventory.domain.repository

import com.braiso22.homeinventory.domain.model.LaundryItem
import com.braiso22.homeinventory.domain.model.LaundryItemId
import com.braiso22.homeinventory.domain.model.LaundryStatus

interface LaundryRepository {
    suspend fun findById(id: LaundryItemId): LaundryItem?
    suspend fun findByStatus(status: LaundryStatus): List<LaundryItem>
    suspend fun save(laundryItem: LaundryItem): LaundryItem
}
