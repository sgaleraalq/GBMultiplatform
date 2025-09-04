package com.gbmultiplatform.design_system.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.components.ImageState.ERROR
import com.gbmultiplatform.design_system.components.ImageState.LOADING
import com.gbmultiplatform.design_system.components.ImageState.NONE
import com.gbmultiplatform.design_system.components.ImageState.SUCCESS
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.utils.SharedImage
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.ic_camera
import org.jetbrains.compose.resources.painterResource

enum class ImageState { NONE, LOADING, SUCCESS, ERROR }

@Composable
fun GBImageBoxRequester(
    modifier: Modifier = Modifier.fillMaxSize(),
    text: String,
    image: SharedImage?,
    iconSize: Dp = 32.dp,
    onClick: () -> Unit = {}
) {
    val state = NONE // todo change

    Row(
        modifier = modifier
            .clickable { onClick() }
            .background(
                color = gray_box_in_black_bg,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        verticalAlignment = CenterVertically
    ) {
        GBText(text, style = gBTypography().bodyMedium)
        Spacer(Modifier.weight(1f))
        when (state) {
            NONE -> {
                Image(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(Res.drawable.ic_camera),
                    contentDescription = null // TODO
                )
            }
            LOADING -> {
                GBProgressDialog(true, White, Modifier.size(iconSize).padding(4.dp), 3f)
            }
            SUCCESS -> {
                GBImage(
                    modifier = Modifier.size(iconSize),
                    image = image?.toByteArray(),
                )
            }
            ERROR -> {}
        }
    }
}
