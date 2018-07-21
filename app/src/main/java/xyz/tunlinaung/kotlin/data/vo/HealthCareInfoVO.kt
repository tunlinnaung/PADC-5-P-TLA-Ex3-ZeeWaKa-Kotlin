package xyz.tunlinaung.kotlin.data.vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "healthcare_info")
open class HealthCareInfoVO(
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "message") val image: String,
        @Embedded val author: AuthorVO,
        @SerializedName("short-description") @ColumnInfo(name = "short-description") val shortDescription: String,
        @SerializedName("published-date") @ColumnInfo(name = "published-date") val publishedDate: String,
        @SerializedName("complete-url") @ColumnInfo(name = "complete-url") val completeUrl: String
        //@SerializedName("info-type") val infoType: String
) {
    init {

    }
}

