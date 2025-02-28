package com.example.goalify

import com.example.goalify.Data.Api.ApiServices
import com.example.goalify.Data.Intefaces.IDetailsRepo
import com.example.goalify.Data.Intefaces.IHomeRepo
import com.example.goalify.Data.Intefaces.ITopAssistRepo
import com.example.goalify.Data.Intefaces.ITopScorerRepo
import com.example.goalify.Data.Repositories.DetailsRepo
import com.example.goalify.Data.Repositories.HomeRepo
import com.example.goalify.Data.Repositories.TopAssistRepo
import com.example.goalify.Data.Repositories.TopScorerRepo
import com.example.goalify.Domain.LiveMatchesDomain
import com.example.goalify.Domain.MatchDetailsSelectorDomain
import com.example.goalify.Domain.MatchGoalScorerDomain
import com.example.goalify.Domain.MatchesDomain
import com.example.goalify.Domain.StateDomain
import com.example.goalify.Domain.TopAssistDomain
import com.example.goalify.Domain.TopCompetitions
import com.example.goalify.Domain.TopScorerDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val apiKey = "79d2cb50de6ba96768d16cf65dbf450351b263da1a2c319dc18140c95a457f41"

        return OkHttpClient.Builder()
            .addInterceptor { chain->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url()
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("APIkey" , apiKey)
                    .build()
                val newRequest = originalRequest.newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }.build()
    }
    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apiv3.apifootball.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit):ApiServices{
        return retrofit.create(ApiServices::class.java)
    }
    @Provides
    @Singleton
    fun getIHomeRepo(apiServices: ApiServices):IHomeRepo{
        return HomeRepo(apiServices)
    }

    @Provides
    @Singleton
    fun getITopCompetitions(repo:IHomeRepo):TopCompetitions{
        return TopCompetitions(repo)
    }

    @Provides
    @Singleton
    fun getIMatchesDomain(repo: IHomeRepo): MatchesDomain{
        return MatchesDomain(repo)
    }
    @Provides
    @Singleton
    fun getILiveMatchesDomain(repo:IHomeRepo):LiveMatchesDomain{
        return LiveMatchesDomain(repo)
    }

    @Provides
    @Singleton
    fun getIDetailsRepo():IDetailsRepo{
        return DetailsRepo()
    }

    @Provides
    @Singleton
    fun getMatchSelectorsDomain(repo:IDetailsRepo):MatchDetailsSelectorDomain{
        return MatchDetailsSelectorDomain(repo)
    }

    @Provides
    @Singleton
    fun getMatchGoalScorerDomain(): MatchGoalScorerDomain {
        return MatchGoalScorerDomain()
    }

    @Provides
    @Singleton
    fun getStateDomain():StateDomain{
        return StateDomain()
    }

    @Provides
    @Singleton
    fun getITopScorerRepo(apiServices: ApiServices):ITopScorerRepo{
        return TopScorerRepo(apiServices)
    }

    @Provides
    @Singleton
    fun getTopScorerDomain(repo: ITopScorerRepo):TopScorerDomain{
        return TopScorerDomain(repo)
    }

    @Provides
    @Singleton
    fun getITopAssistRepo(apiServices: ApiServices):ITopAssistRepo{
        return TopAssistRepo(apiServices)
    }

    @Provides
    @Singleton
    fun getTopAssistDomain(repo:ITopAssistRepo):TopAssistDomain{
        return TopAssistDomain(repo)
    }

}