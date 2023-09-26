package com.brasiliki.avengers.application.web.resource.request

import com.brasiliki.avengers.domain.avanger.Avenger
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class AvengerRequest(
    @field: NotNull
    @field: NotBlank
    @field: NotEmpty
    val nick: String,
    @field: NotNull
    @field: NotBlank
    @field: NotEmpty
    val person: String,
    val description: String? = null,
    val history: String? = null
) {
    fun toAvenger() = Avenger(
        nick = nick,
        person = person,
        description = description,
        history = history
    )

    /**companion object {
    fun toAvenger(avenger: AvengerRequest) = Avenger(
    nick = avenger.nick,
    person = avenger.person,
    description = avenger.description,
    history = avenger.history
    )}**/

    companion object {
        fun to(id: Long, request: AvengerRequest) = Avenger(
            id = id,
            nick = request.nick,
            person = request.person,
            description = request.description,
            history = request.history
        )
    }
}

