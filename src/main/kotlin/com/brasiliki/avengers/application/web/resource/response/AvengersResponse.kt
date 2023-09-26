package com.brasiliki.avengers.application.web.resource.response

import com.brasiliki.avengers.domain.avanger.Avenger


data class AvengersResponse(
    val id: Long?,
    val nick: String,
    val person: String,
    val description: String? = "",
    val history: String? = ""
) {
    companion object {
        fun from(avenger: Avenger) = AvengersResponse(
            id = avenger.id,
            nick = avenger.nick,
            person = avenger.person,
            description = avenger.description,
            history = avenger.history
        )

    }
}


