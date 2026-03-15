package com.braiso22.homeinventory.domain.model

data class Product(
    val id: ProductId = ProductId(),
    val name: ProductName,
    val brand: Brand,
    val variant: Variant,
    val category: Category,
    val threshold: Quantity
)
