package com.brasiliki.avengers.resource.avenger

import com.brasiliki.avengers.domain.avanger.Avenger
import com.brasiliki.avengers.domain.avanger.AvengerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class AvengerRepositoryImplements(
    @Autowired private val avengerEntityRepository: AvengerEntityRepository
) : AvengerRepository {
    override fun getDetail(id: Long): Avenger? = avengerEntityRepository.findByIdOrNull(id)?.toAvenger()

    override fun getAvengers(): List<Avenger> = avengerEntityRepository.findAll().map { it.toAvenger() }

    override fun create(avenger: Avenger): Avenger = avengerEntityRepository
        .save(AvengerEntity.from(avenger))
        .toAvenger()

    override fun delete(id: Long) = avengerEntityRepository.deleteById(id)

    override fun update(avenger: Avenger): Avenger = avengerEntityRepository
        .save(AvengerEntity.from(avenger))
        .toAvenger()
}