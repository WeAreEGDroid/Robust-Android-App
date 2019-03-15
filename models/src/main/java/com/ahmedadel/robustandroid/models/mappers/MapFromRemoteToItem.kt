package com.ahmedadel.robustandroid.models.mappers

import com.ahmedadel.robustandroid.models.EntityModel

/**
 * Created at Tito on 3/15/19
 *
 * Map from remote to entity use case model.
 */

interface MapFromRemoteToItem<R, L, I : EntityModel> {

    fun mapFromRemoteToItem(model: R): I
    fun mapFromLocalToItem(model: L): I
    fun mapFromRemoteToLocal(model: R): L

}