package com.brasiliki.avengers.domain.avanger

interface AvengerRepository {
    fun getDetail(id : Long): Avenger?
    fun getAvengers(): List<Avenger>
    fun create(avenger: Avenger): Avenger
    fun delete(id: Long)
    fun update(id: Avenger): Avenger
}