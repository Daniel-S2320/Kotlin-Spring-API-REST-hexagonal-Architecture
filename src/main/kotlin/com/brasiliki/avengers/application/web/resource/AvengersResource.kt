package com.brasiliki.avengers.application.web.resource

import com.brasiliki.avengers.application.web.resource.request.AvengerRequest
import com.brasiliki.avengers.application.web.resource.response.AvengersResponse
import com.brasiliki.avengers.domain.avanger.AvengerRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

private const val API_PATH = "/v1/api/avenger"

@RestController
@RequestMapping(value = [API_PATH])

class AvengersResource(@Autowired private val repository: AvengerRepository) {

    @GetMapping
    fun getAvengers() = repository.getAvengers()
        .map { AvengersResponse.from(it) }
        .let {
            ResponseEntity.ok().body(it)
        }

    @GetMapping("{id}/details")
    fun getDetails(@PathVariable("id") id: Long) = repository.getDetail(id)?.let {
        ResponseEntity.ok().body(AvengersResponse.from(it))
    }

    @PostMapping
    fun createAvenger(@Valid @RequestBody request: AvengerRequest) = request
        .toAvenger().run {
            repository.create(this)
        }.let {
            ResponseEntity.created(URI("$API_PATH/${it.id}"))
                .body(AvengersResponse.from(it))
        }

    /**
    fun createAvenger(@Valid @RequestBody request: AvengerRequest): ResponseEntity<Any> = repository
    .create(AvengerRequest.toAvenger(request))
    .let {
    ResponseEntity.created(URI("/api/avengers/${it.id}"))
    .body(avengersResponse)
    }
     **/

    @PutMapping("{id}")
    fun updateAvenger(
        @Valid @RequestBody request: AvengerRequest, @PathVariable("id") avenger: Long
    ) = repository.getDetail(avenger)?.let {
        AvengerRequest.to(avenger, request).apply {
            repository.update(this)
        }.let { avenger ->
            ResponseEntity.accepted().body(AvengersResponse.from(avenger))
        }
    } ?: ResponseEntity.notFound().build<Void>()

    @DeleteMapping("{id}")
    fun deleteAvenger(@PathVariable("id") id: Long) =
        repository.delete(id).let {
            ResponseEntity.accepted().build<Void>()
        }
}