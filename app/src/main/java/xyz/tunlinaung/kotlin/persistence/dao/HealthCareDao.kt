package xyz.tunlinaung.kotlin.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import xyz.tunlinaung.kotlin.data.vo.HealthCareInfoVO

@Dao
interface HealthCareDao {

    // TODO how to varargs
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHealthcareInfo(healthcareInfos: List<HealthCareInfoVO>): LongArray

    @Query("SELECT * FROM healthcare_info")
    fun getAllHealthcareInfos(): List<HealthCareInfoVO>

    @Query("SELECT * FROM healthcare_info WHERE id = :id")
    fun getHealthcareInfosById(id: String): HealthCareInfoVO

    @Query("SELECT * FROM healthcare_info WHERE id = :id")
    fun getHealthcareInfosByIdLD(id: String): LiveData<HealthCareInfoVO>

}