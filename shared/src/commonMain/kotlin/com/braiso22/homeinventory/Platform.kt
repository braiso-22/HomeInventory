package com.braiso22.homeinventory

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform