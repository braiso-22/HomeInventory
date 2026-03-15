package com.braiso22.homeinventory.domain.repository

import com.braiso22.homeinventory.domain.model.ShoppingList

interface ShoppingListRepository {
    suspend fun findActive(): ShoppingList?
    suspend fun save(shoppingList: ShoppingList): ShoppingList
}
