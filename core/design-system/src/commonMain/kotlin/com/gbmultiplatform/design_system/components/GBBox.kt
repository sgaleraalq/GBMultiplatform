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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.utils.ImagePath
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.ic_camera
import org.jetbrains.compose.resources.painterResource

//enum class ImageState { NONE, LOADING, SUCCESS, ERROR }

@Composable
fun GBImageBoxRequester(
    modifier: Modifier = Modifier.fillMaxSize(),
    text: String,
    image: ImagePath?,
    iconSize: Dp = 32.dp,
    onClick: () -> Unit = {}
) {
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
        if (image != null) {
            GBImage(
                modifier = Modifier.size(iconSize),
                image = image,
            )
        } else {
            Image(
                modifier = Modifier.size(iconSize),
                painter = painterResource(Res.drawable.ic_camera),
                contentDescription = null // TODO
            )
        }
    }
}
