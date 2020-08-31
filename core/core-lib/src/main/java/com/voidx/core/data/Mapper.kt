package com.voidx.core.data

interface Mapper<From, To> {

    fun map(from: From): To
}