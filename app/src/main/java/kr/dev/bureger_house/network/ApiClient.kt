package kr.dev.bureger_house.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {


     private const val BASE_URl = "https://cbu.uz/uz/"


    fun getRetrofit():Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}