package com.gbmultiplatform.data.mappers

import com.gbmultiplatform.data.db.entities.PlayerInformationEntity
import com.gbmultiplatform.data.network.response.PlayerInformationResponse
import com.gbmultiplatform.domain.model.player.PlayerInformationModel
import com.gbmultiplatform.domain.model.player.mapPosition

object PlayerInformationMapper :
    Mapper<PlayerInformationResponse, PlayerInformationModel, PlayerInformationEntity>
{
    override fun asResponse(domain: PlayerInformationModel) =
        PlayerInformationResponse(
            id = domain.id,
            name = domain.name,
            dorsal = domain.dorsal,
            position = domain.position?.name ?: "",
            faceImage = domain.faceImage,
            bodyImage = domain.bodyImage
        )

    override fun asEntity(domain: PlayerInformationModel): PlayerInformationEntity? {
        return null
    }

    override fun entityAsDomain(entity: PlayerInformationEntity): PlayerInformationModel? {
        return null
    }

    override fun responseAsModel(response: PlayerInformationResponse): PlayerInformationModel? =
        PlayerInformationModel(
            id = response.id,
            name = response.name,
            dorsal = response.dorsal,
            position = mapPosition(response.position),
            faceImage = response.faceImage,
            bodyImage = response.bodyImage
        )
}

fun List<PlayerInformationResponse>.asModel() =
    this.mapNotNull { PlayerInformationMapper.responseAsModel(it) }
