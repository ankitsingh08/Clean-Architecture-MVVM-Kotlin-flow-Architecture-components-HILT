package ankit.com.starwarssample.di

import ankit.com.data.repository.StarWarsCharacterDetailsRepositoryImpl
import ankit.com.data.repository.StarWarsRepositoryImpl
import ankit.com.domain.repository.StarWarCharacterDetailRepository
import ankit.com.domain.repository.StarWarsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by AnkitSingh on 12/12/20.
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStarWarsRepository(starWarsRepository: StarWarsRepositoryImpl): StarWarsRepository

    @Binds
    @Singleton
    abstract fun bindStarWarCharacterDetailRepository(starWarCharacterDetailRepository: StarWarsCharacterDetailsRepositoryImpl)
            : StarWarCharacterDetailRepository
}