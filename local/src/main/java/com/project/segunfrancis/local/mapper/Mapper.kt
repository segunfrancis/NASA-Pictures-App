package com.project.segunfrancis.local.mapper

/**
 * Created by SegunFrancis
 */

interface Mapper<I, O> {
    fun mapLocalToData(data: I): O
}