package com.lovevery.exam.base.mapper

interface BaseMapper<I, O> {

    fun map(input: I): O
}