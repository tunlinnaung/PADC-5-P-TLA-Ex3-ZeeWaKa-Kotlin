package xyz.tunlinaung.kotlin.network

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import xyz.tunlinaung.kotlin.network.responses.HealthCareInfoResponse


interface ZeeWaKaApi {

    @FormUrlEncoded
    @POST("GetHealthcareInfo.php")
    fun getHealthcareInfo(@Field("access_token") accessToken: String): Observable<HealthCareInfoResponse>

}