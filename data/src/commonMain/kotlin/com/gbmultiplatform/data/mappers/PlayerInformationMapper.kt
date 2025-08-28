package com.gbmultiplatform.data.mappers

import com.gbmultiplatform.data.db.entities.PlayerInformationEntity
import com.gbmultiplatform.data.network.response.PlayerInformationResponse
import com.gbmultiplatform.domain.model.player.PlayerInformationModel

object PlayerInformationMapper :
    Mapper<PlayerInformationResponse, PlayerInformationModel, PlayerInformationEntity>
{
    override fun asResponse(domain: PlayerInformationModel) =
        PlayerInformationResponse(
            id = domain.id,
            name = domain.name,
            dorsal = domain.dorsal,
            position = domain.position.name,
            faceImage = domain.faceImage,
            bodyImage = domain.bodyImage
        )

    override fun asEntity(domain: PlayerInformationModel): PlayerInformationEntity? {
        return null
    }

    override fun asDomain(entity: PlayerInformationEntity): PlayerInformationModel? {
        return null
    }

    override fun asDomain(response: PlayerInformationResponse): PlayerInformationModel? {
        return null
    }
}
