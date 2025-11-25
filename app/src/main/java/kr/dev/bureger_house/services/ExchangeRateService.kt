package kr.dev.bureger_house.services

import kr.dev.bureger_house.models.RateModel
import retrofit2.Call
import retrofit2.http.GET

interface ExchangeRateService {


    @GET("arkhiv-kursov-valyut/json/")
    fun getRates():Call<List<RateModel>>
}