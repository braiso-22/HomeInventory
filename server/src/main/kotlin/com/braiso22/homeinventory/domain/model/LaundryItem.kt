package com.braiso22.homeinventory.domain.model

data class LaundryItem(
    val id: LaundryItemId = LaundryItemId(),
    val name: ProductName,
    val category: Category,
    val status: LaundryStatus = LaundryStatus.CLEAN,
    val location: Location
)

enum class LaundryStatus {
    CLEAN, DIRTY, WASHING, DRYING
}
