package org.gaztelubira.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform