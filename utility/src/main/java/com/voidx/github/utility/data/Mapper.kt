package com.voidx.github.utility.data

interface Mapper<From, To> {

    fun map(from: From): To
}