package xyz.tunlinaung.kotlin.network.responses

import com.google.gson.annotations.SerializedName
import xyz.tunlinaung.kotlin.data.vo.HealthCareInfoVO


data class HealthCareInfoResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("healthcare-info") val healthcareInfo: List<HealthCareInfoVO> = ArrayList()
)