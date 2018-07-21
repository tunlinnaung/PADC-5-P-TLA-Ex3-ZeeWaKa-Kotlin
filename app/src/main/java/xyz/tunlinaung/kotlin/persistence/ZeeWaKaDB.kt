package xyz.tunlinaung.kotlin.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import xyz.tunlinaung.kotlin.data.vo.HealthCareInfoVO
import android.arch.persistence.room.Room
import android.arch.persistence.room.TypeConverters
import android.content.Context
import xyz.tunlinaung.kotlin.persistence.dao.HealthCareDao
import xyz.tunlinaung.kotlin.persistence.typeconverters.AuthorJsonTypeConverter

@Database(entities = [(HealthCareInfoVO::class)], version = 3, exportSchema = false)
@TypeConverters(AuthorJsonTypeConverter::class)
abstract class ZeeWaKaDB : RoomDatabase() {

    companion object {
        private const val DB_NAME = "ZeeWaKa.DB"
        private var INSTANCE: ZeeWaKaDB? = null

        fun getDatabase(context: Context): ZeeWaKaDB {
            return INSTANCE ?: Room.databaseBuilder(context, ZeeWaKaDB::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()  //TODO to ask
                    .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                    .build()
        }
    }

    abstract fun healthCareDao(): HealthCareDao

}