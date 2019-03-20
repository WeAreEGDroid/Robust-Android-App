package com.ahmedadel.robustandroid.presentation.mvi.movie.di

import androidx.lifecycle.ViewModel
import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.ViewModelKey
import com.ahmedadel.robustandroid.presentation.mvi.movie.MovieDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created at Tito on 3/20/19
 */

@Module
abstract class MovieDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    @PresentationScope
    internal abstract fun provideHomeViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

}