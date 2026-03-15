package com.braiso22.homeinventory.domain.model

import kotlin.time.Instant

data class Purchase(
    val id: PurchaseId = PurchaseId(),
    val date: Instant,
    val totalPrice: Money,
    val items: List<PurchaseItem>
)

data class PurchaseItem(
    val productId: ProductId,
    val quantity: Quantity,
    val unitPrice: Money
)
