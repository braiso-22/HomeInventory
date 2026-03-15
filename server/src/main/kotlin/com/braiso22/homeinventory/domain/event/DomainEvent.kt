package com.braiso22.homeinventory.domain.event

import com.braiso22.homeinventory.domain.model.*
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

sealed class DomainEvent {
    val eventId: UUID = UUID.randomUUID()
    val occurredAt: Instant = Instant.now()
}

// Purchase Events
data class PurchaseRecorded(val purchaseId: PurchaseId, val date: Instant, val totalPrice: Money) : DomainEvent()
data class PurchaseItemDetectedFromOCR(val rawText: String, val confidence: Double) : DomainEvent()
data class PurchaseItemAdded(val purchaseId: PurchaseId, val productId: ProductId, val quantity: Quantity, val unitPrice: Money) : DomainEvent()
data class PurchasePriceRecorded(val productId: ProductId, val price: Money, val date: Instant) : DomainEvent()

// Inventory Events
data class StockAdded(val productId: ProductId, val quantity: Quantity, val location: Location) : DomainEvent()
data class StockConsumed(val stockItemId: StockItemId, val productId: ProductId) : DomainEvent()
data class StockAdjustedAfterReview(val productId: ProductId, val newQuantity: Quantity, val reason: String) : DomainEvent()
data class StockMovedToLocation(val stockItemId: StockItemId, val newLocation: Location) : DomainEvent()

// Expiration and Alerts
data class StockItemExpirationRecorded(val stockItemId: StockItemId, val expirationDate: LocalDate) : DomainEvent()
data class StockItemAboutToExpire(val stockItemId: StockItemId, val expirationDate: LocalDate) : DomainEvent()
data class StockItemExpired(val stockItemId: StockItemId) : DomainEvent()
data class ProductBelowThreshold(val productId: ProductId, val currentStock: Quantity, val threshold: Quantity) : DomainEvent()
data class ProductAddedToShoppingList(val productId: ProductId, val quantity: Quantity) : DomainEvent()

// Laundry Events
data class ClothesAddedToLaundry(val itemId: LaundryItemId) : DomainEvent()
data class LaundryCompleted(val batchId: UUID) : DomainEvent() // Batch might need its own ID VO if expanded
data class ClothesReturnedToCloset(val itemId: LaundryItemId, val location: Location) : DomainEvent()
