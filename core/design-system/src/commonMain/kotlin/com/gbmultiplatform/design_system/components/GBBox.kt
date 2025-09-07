package com.gbmultiplatform.design_system.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale.Companion.Fit
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.utils.ImagePath
import com.gbmultiplatform.domain.utils.resolveImageFromPath
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.description_camera_icon
import gbmultiplatform.core.design_system.generated.resources.ic_camera
import gbmultiplatform.core.design_system.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GBImageBoxRequester(
    modifier: Modifier = Modifier.fillMaxSize(),
    text: String,
    image: ImagePath?,
    iconSize: Dp = 32.dp,
    onClick: () -> Unit = {},
) {
    val imageByteArray = resolveImageFromPath(image?.path)
    var showZoomDialog by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .background(
                color = gray_box_in_black_bg,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = CenterVertically
    ) {
        GBText(
            modifier = Modifier
                .clickable { onClick() }
                .padding(16.dp)
                .weight(1f),
            text = text,
            style = gBTypography().bodyMedium
        )

        if (image == null) {
            Image(
                modifier = Modifier.clickable { onClick() }.padding(12.dp).size(iconSize),
                painter = painterResource(Res.drawable.ic_camera),
                contentDescription = stringResource(Res.string.description_camera_icon)
            )
        } else {
            GBImage(
                modifier = Modifier
                    .clickable { if (imageByteArray != null) showZoomDialog = true else onClick() }
                    .padding(12.dp)
                    .size(iconSize),
                image = imageByteArray
            )
        }
    }

    if (showZoomDialog && imageByteArray != null) {
        ZoomableImage(
            image = imageByteArray,
            dismiss = { showZoomDialog = false }
        )
    }
}

@Composable
fun ZoomableImage(
    image: ByteArray,
    dismiss: () -> Unit
) {
    GBDialog(
        show = true,
        dismiss = { dismiss() }
    ) {
        Box(
            modifier = Modifier.background(Black),
            contentAlignment = Center
        ) {
            GBImage(
                image = image,
                contentScale = Fit
            )
            GBIcon(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable { dismiss() },
                icon = Res.drawable.ic_close,
                size = 32.dp
            )
        }
    }
}
