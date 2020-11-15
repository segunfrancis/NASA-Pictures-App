package com.project.segunfrancis.nasapicturesapp.mapper

/**
 * Created by SegunFrancis
 */
interface Mapper<I, O> {
    fun mapDomainToAppLayer(data: I): O
}