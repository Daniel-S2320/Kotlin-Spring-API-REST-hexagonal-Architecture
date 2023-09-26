package com.brasiliki.avengers.resource.avenger

import com.brasiliki.avengers.domain.avanger.Avenger
import jakarta.persistence.*

@Entity
data class AvengerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(nullable = false)
    val nick: String,
    @Column(nullable = false)
    val person: String,
    val description: String?,
    val history: String?
) {
    fun toAvenger() = Avenger(
        id, nick, person, description, history
    )

    companion object {
        fun from(avenger: Avenger) = AvengerEntity(
            nick = avenger.nick,
            person = avenger.person,
            description = avenger.description,
            history = avenger.history,
            id = avenger.id
        )
    }
}
