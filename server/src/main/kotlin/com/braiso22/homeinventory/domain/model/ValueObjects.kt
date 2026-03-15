package com.braiso22.homeinventory.domain.model

import java.util.UUID

@JvmInline
value class ProductId(val value: UUID = UUID.randomUUID())

@JvmInline
value class StockItemId(val value: UUID = UUID.randomUUID())

@JvmInline
value class PurchaseId(val value: UUID = UUID.randomUUID())

@JvmInline
value class LaundryItemId(val value: UUID = UUID.randomUUID())

@JvmInline
value class ShoppingListId(val value: UUID = UUID.randomUUID())

@JvmInline
value class Quantity(val value: Int) {
    init {
        require(value >= 0) { "Quantity cannot be negative" }
    }
}

@JvmInline
value class Money(val amount: Double) {
    init {
        require(amount >= 0.0) { "Amount cannot be negative" }
    }
    operator fun plus(other: Money) = Money(this.amount + other.amount)
}

@JvmInline
value class ProductName(val value: String) {
    init {
        require(value.isNotBlank()) { "Product name cannot be blank" }
    }
}

@JvmInline
value class Brand(val value: String)

@JvmInline
value class Category(val value: String)

@JvmInline
value class Location(val value: String)

@JvmInline
value class Variant(val value: String)
