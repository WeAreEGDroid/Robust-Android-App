package com.ahmedadel.robustandroid.core.di.scheduler

import com.ahmedadel.robustandroid.core.di.PresentationScope
import dagger.Module
import dagger.Provides

/**
 * Created at Tito on 3/16/19
 *
 * Provider class for making background and foreground threads that related to RX stuff. this class will be helpful
 * for unit testing purpose to change the scheduler provider during the unit testing phase and not making the
 * scheduler provider attached to the architecture.
 */

@Module
class BaseSchedulerProviderModule {

    @Provides
    @PresentationScope
    fun providesTaskBaseScheduler(): BaseSchedulerProvider = SchedulerProvider()

}