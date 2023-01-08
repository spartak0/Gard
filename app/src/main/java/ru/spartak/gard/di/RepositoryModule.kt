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
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.spartak.gard.data.FirebaseRepositoryImpl
import ru.spartak.gard.data.FirebaseService
import ru.spartak.gard.domain.FirebaseRepository
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
    fun provideFirebaseService(@ApplicationContext context: Context ,auth:FirebaseAuth, database: FirebaseDatabase): FirebaseService {
        return FirebaseService(context,auth, database)
    }

    @Provides
    @Singleton
    fun provideFirebaseRepository(firebaseService: FirebaseService): FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseService)
    }
}