package com.braiso22.homeinventory.domain.model

data class ShoppingList(
    val id: ShoppingListId = ShoppingListId(),
    val items: List<ShoppingListItem>
)

data class ShoppingListItem(
    val productId: ProductId,
    val quantity: Quantity,
    val isSuggested: Boolean = false,
    val isChecked: Boolean = false
)
