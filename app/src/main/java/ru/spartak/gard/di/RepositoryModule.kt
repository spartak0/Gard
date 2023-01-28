package ru.spartak.gard.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.spartak.gard.data.db.firebase.FirebaseRepositoryImpl
import ru.spartak.gard.data.db.firebase.FirebaseService
import ru.spartak.gard.data.network.ApiRepositoryImpl
import ru.spartak.gard.data.network.api.RetrofitApi
import ru.spartak.gard.domain.mapper.StatsMapper
import ru.spartak.gard.domain.repository.ApiRepository
import ru.spartak.gard.domain.repository.FirebaseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return Firebase.database
    }


    @Provides
    @Singleton
    fun provideFirebaseService(auth: FirebaseAuth): FirebaseService {
        return FirebaseService(auth)
    }


    @Provides
    @Singleton
    fun provideFirebaseRepository(firebaseService: FirebaseService): FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseService)
    }


    @Provides
    @Singleton
    fun provideStatsMapper(): StatsMapper {
        return StatsMapper()
    }


    @Provides
    @Singleton
    fun provideApiRepository(retrofitApi: RetrofitApi, statsMapper: StatsMapper): ApiRepository {
        return ApiRepositoryImpl(retrofitApi,statsMapper)
    }
}