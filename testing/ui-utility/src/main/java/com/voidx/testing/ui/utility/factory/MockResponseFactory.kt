package com.voidx.testing.ui.utility.factory

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse
import java.io.IOException

fun createMockResponse(code: Int, fixtureName: String): MockResponse {
    return MockResponse()
        .setResponseCode(code)
        .setBody(readFixture(fixtureName))
}

fun createMockResponse(code: Int): MockResponse {
    return MockResponse()
        .setResponseCode(code)
}

fun readFixture(file: String): String {
    val path = "fixtures/$file"
    return try {
        val assets = InstrumentationRegistry.getInstrumentation().context.assets
        val openFile = assets.open(path)
        val size = openFile.available()
        val buffer = ByteArray(size)
        openFile.read(buffer)
        openFile.close()
        String(buffer, Charsets.UTF_8)
    } catch (e: IOException) {
        Log.e("TEST", "readFixture: ", e)
        return ""
    }
}