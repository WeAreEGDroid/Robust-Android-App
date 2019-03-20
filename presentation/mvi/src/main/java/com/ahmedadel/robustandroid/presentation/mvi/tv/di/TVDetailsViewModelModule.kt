package com.ahmedadel.robustandroid.presentation.mvi.tv.di

import androidx.lifecycle.ViewModel
import com.ahmedadel.robustandroid.core.di.PresentationScope
import com.ahmedadel.robustandroid.core.di.ViewModelKey
import com.ahmedadel.robustandroid.presentation.mvi.tv.TVDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created at Tito on 3/20/19
 */

@Module
abstract class TVDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TVDetailsViewModel::class)
    @PresentationScope
    internal abstract fun provideHomeViewModel(tvDetailsViewModel: TVDetailsViewModel): ViewModel

}