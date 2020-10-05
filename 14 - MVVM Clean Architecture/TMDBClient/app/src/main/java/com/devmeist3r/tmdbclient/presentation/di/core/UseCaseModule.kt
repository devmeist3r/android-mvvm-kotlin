package com.devmeist3r.tmdbclient.presentation.di.core

import com.devmeist3r.tmdbclient.domain.repository.ArtistRepository
import com.devmeist3r.tmdbclient.domain.repository.MovieRepository
import com.devmeist3r.tmdbclient.domain.repository.TvShowRepository
import com.devmeist3r.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

  @Provides
  fun provideGetMovieUseCase(movieRepository: MovieRepository):GetMoviesUseCase {
    return GetMoviesUseCase(movieRepository)
  }
  @Provides
  fun provideUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase {
    return UpdateMoviesUseCase(movieRepository)
  }

  @Provides
  fun provideGetTvShowUseCase(tvShowRepository: TvShowRepository):GetTvShowsUseCase {
    return GetTvShowsUseCase(tvShowRepository)
  }
  @Provides
  fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository):UpdateTvShowsUseCase {
    return UpdateTvShowsUseCase(tvShowRepository)
  }

  @Provides
  fun provideGetArtistUseCase(artistRepository: ArtistRepository):GetArtistsUseCase {
    return GetArtistsUseCase(artistRepository)
  }
  @Provides
  fun provideUpdateArtistUseCase(artistRepository: ArtistRepository):UpdateArtistsUseCase {
    return UpdateArtistsUseCase(artistRepository)
  }

}