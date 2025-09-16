package com.gbmultiplatform.design_system.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
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
import androidx.compose.ui.window.Dialog
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.utils.CommonImage
import com.gbmultiplatform.domain.utils.CommonImage.*
import com.gbmultiplatform.domain.utils.SharedImagesBridge
import gbmultiplatform.core.design_system.generated.resources.Res
import gbmultiplatform.core.design_system.generated.resources.description_camera_icon
import gbmultiplatform.core.design_system.generated.resources.ic_camera
import gbmultiplatform.core.design_system.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GBImageBoxRequester(
    modifier: Modifier,
    text: String,
    iconSize: Dp = 32.dp,
    commonImage: CommonImage?,
    imageLoader: SharedImagesBridge,
    onClick: () -> Unit = {},
) {
    var showZoomDialog by remember { mutableStateOf(false) }

    val image by produceState<ByteArray?>(initialValue = null, commonImage) {
        value = if (commonImage != null) {
            imageLoader.loadImage(
                uri = commonImage.uri,
                maxWidth = 800,
                maxHeight = 800,
                quality = 80,
                isFrontCamera = when (commonImage) {
                    is FromFrontCamera -> true
                    is FromBackCamera  -> false
                    is FromGallery     -> false
                }
            )
        } else {
            null
        }
    }
    val img = image

    Row(
        modifier = modifier
            .background(gray_box_in_black_bg, RoundedCornerShape(8.dp)),
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

        if (img == null) {
            Image(
                modifier = Modifier
                    .clickable { onClick() }
                    .padding(12.dp)
                    .size(iconSize - 10.dp),
                painter = painterResource(Res.drawable.ic_camera),
                contentDescription = stringResource(Res.string.description_camera_icon)
            )
        } else {
            GBImage(
                modifier = Modifier
                    .clickable { showZoomDialog = true }
                    .padding(12.dp)
                    .size(iconSize),
                image = img
            )
        }
    }

    if (showZoomDialog) {
        img?.let { bytes ->
            ZoomableImage(
                image = bytes,
                dismiss = { showZoomDialog = false }
            )
        }
    }
}

@Composable
fun ZoomableImage(
    image: ByteArray,
    dismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { dismiss() }
    ) {
        Box(
            modifier = Modifier.background(Black),
            contentAlignment = Center
        ) {
            GBImage(
                modifier = Modifier.fillMaxWidth(),
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
