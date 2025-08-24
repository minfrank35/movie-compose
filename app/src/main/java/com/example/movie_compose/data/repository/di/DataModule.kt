package com.example.movie_compose.data.repository.di

import com.example.movie_compose.data.repository.HomeRepository
import com.example.movie_compose.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

  @Binds
  fun bindsMainRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

//  @Binds
//  fun bindsDetailRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository
}