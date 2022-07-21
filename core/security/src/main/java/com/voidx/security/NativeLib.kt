package com.voidx.security

object Keys {

    external fun apiKey(): String

    init {
        System.loadLibrary("security")
    }
}
