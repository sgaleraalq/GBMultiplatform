package com.gbmultiplatform.presentation.screens.insert_player

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import com.gbmultiplatform.domain.utils.CommonImage
import com.gbmultiplatform.presentation.screens.insert_player.InsertPlayerNavArgs.ReviewState.FACE
import kotlinx.serialization.Serializable

@Stable
class InsertPlayerFlowState (
    facePath: String? = null,
    bodyPath: String? = null
){
    var faceImgPath by mutableStateOf(facePath)
    var bodyImgPath by mutableStateOf(bodyPath)
}

val InsertPlayerFlowSaver = Saver<InsertPlayerFlowState, Map<String, String?>>(
    save = { mapOf("face" to it.faceImgPath, "body" to it.bodyImgPath) },
    restore = { InsertPlayerFlowState(it["face"], it["body"]) }
)

@Serializable
data class InsertPlayerNavArgs(
    val review: ReviewState = FACE,
    val faceImg: CommonImage? = null,
    val bodyImg: CommonImage? = null
) {
    enum class ReviewState {
        FACE, BODY
    }
}
