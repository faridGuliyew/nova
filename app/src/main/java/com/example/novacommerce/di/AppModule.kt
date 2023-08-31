package com.example.novacommerce.di

import android.content.Context
import com.example.novacommerce.common.utils.PrefManager
import com.example.novacommerce.data.remote.CommerceApi
import com.example.novacommerce.data.repository.CommerceRepositoryImpl
import com.example.novacommerce.data.repository.FirebaseRepositoryImpl
import com.example.novacommerce.data.source.RemoteSource
import com.example.novacommerce.data.source.RemoteSourceImpl
import com.example.novacommerce.domain.repository.CommerceRepository
import com.example.novacommerce.domain.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFirebaseRepository(auth : FirebaseAuth) : FirebaseRepository{
        return FirebaseRepositoryImpl(auth)
    }

    @Singleton
    @Provides
    fun provideCommerceRepository(remoteSource : RemoteSource) : CommerceRepository{
        return CommerceRepositoryImpl(remoteSource)
    }

    @Singleton
    @Provides
    fun providePrefManager(@ApplicationContext context : Context) : PrefManager{
        return PrefManager(context)
    }

    @Singleton
    @Provides
    fun provideRemoteSource(commerceApi: CommerceApi, firestore: FirebaseFirestore) : RemoteSource{
        return RemoteSourceImpl(
            commerceApi,firestore)
    }
}