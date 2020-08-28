package com.voidx.testing.unit.utility.factory

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

object MockObjectFactory {

    inline fun <reified T> getObjects(fileName: String): List<T> {
        val json = getJson(fileName)
        val type = TypeToken.getParameterized(List::class.java, T::class.java).type
        return Gson().fromJson(json, type)
    }

    inline fun <reified T> getObject(fileName: String): T {
        return getObject(fileName, object: TypeToken<T>() {}.type)
    }

    inline fun <reified T> getObject(fileName: String, type: Type): T {
        val json = getJson(fileName)
        return Gson().fromJson(json, type)
    }

    fun getJson(fileName: String): String? {
        return try {
            val classLoader = ClassLoader.getSystemClassLoader()
            val inputStream = classLoader.getResourceAsStream(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()

            String(buffer, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}