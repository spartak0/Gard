package ru.spartak.gard.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.spartak.gard.data.db.firebase.FirebaseRepositoryImpl
import ru.spartak.gard.data.db.firebase.FirebaseService
import ru.spartak.gard.data.network.ApiRepositoryImpl
import ru.spartak.gard.data.network.api.RetrofitApi
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
    fun provideFirebaseService(@ApplicationContext context: Context ,auth:FirebaseAuth): FirebaseService {
        return FirebaseService(context,auth)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(firebaseService: FirebaseService): FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseService)
    }

    @Provides
    @Singleton
    fun provideApiRepository(retrofitApi: RetrofitApi):ApiRepository {
        return ApiRepositoryImpl(retrofitApi)
    }
}