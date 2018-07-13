package xyz.tunlinaung.kotlin.data.vo

import android.arch.persistence.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class AuthorVO (
        @SerializedName("author-id") @ColumnInfo(name = "author-id") val authorId: Int,
        @SerializedName("author-name") @ColumnInfo(name = "author-name") val authorName: String,
        @SerializedName("author-picture") @ColumnInfo(name = "author-picture") val authorPicture: String
)