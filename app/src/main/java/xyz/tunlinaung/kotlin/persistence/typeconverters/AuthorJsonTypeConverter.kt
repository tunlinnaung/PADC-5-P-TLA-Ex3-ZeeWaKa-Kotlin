package xyz.tunlinaung.kotlin.persistence.typeconverters

import android.arch.persistence.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import xyz.tunlinaung.kotlin.data.vo.AuthorVO

class AuthorJsonTypeConverter {

    @TypeConverter // note this annotation
    fun fromJson(author: AuthorVO?): String? {
        if (author == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<AuthorVO>() {

        }.type
        return gson.toJson(author, type)
    }

    @TypeConverter // note this annotation
    fun toOptionValuesList(authorStr: String?): AuthorVO? {
        if (authorStr == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<AuthorVO>() {

        }.type
        return gson.fromJson(authorStr, type)
    }

}