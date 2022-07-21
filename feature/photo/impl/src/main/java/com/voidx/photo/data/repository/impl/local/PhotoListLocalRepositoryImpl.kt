package com.voidx.photo.data.repository.impl.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.voidx.photo.data.model.Photo
import com.voidx.photo.data.model.ResultPhotos
import com.voidx.photo.data.repository.PhotoListRepository
import io.reactivex.rxjava3.core.Observable
import java.io.InputStream
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class PhotoListLocalRepositoryImpl @Inject constructor(
    private val context: Context
): PhotoListRepository {

    override fun getPhotos(page: Int): Observable<ResultPhotos> {
        TODO("Not yet implemented")
//        return Observable.create { emitter ->
//            val result = getObject("photos.json")
//
//            if (result == null) {
//                emitter.onNext(emptyList())
//            } else {
//                emitter.onNext(result)
//            }
//        }
    }

    private fun getObject(file: String): List<Photo>? {
        return getJsonString(file)?.let {
            val itemType = object : TypeToken<List<Photo>>() {}.type
            return@let Gson().fromJson(it, itemType)
        }
    }

    private fun getJsonString(file: String): String? {
        var result: String? = null
        var stream: InputStream? = null
        try {
            stream = context.assets.open(file)
            val formArray = ByteArray(stream.available())
            stream.read(formArray)
            result = String(formArray, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                stream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return result
    }
}
