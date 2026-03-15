package com.braiso22.homeinventory.domain.model

import java.time.LocalDate

data class StockItem(
    val id: StockItemId = StockItemId(),
    val productId: ProductId,
    val purchaseId: PurchaseId?,
    val expirationDate: LocalDate?,
    val location: Location,
    val status: StockStatus = StockStatus.AVAILABLE
)

enum class StockStatus {
    AVAILABLE, CONSUMED, EXPIRED
}
