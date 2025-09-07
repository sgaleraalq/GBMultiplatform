package com.gbmultiplatform.design_system.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.window.Dialog
import com.gbmultiplatform.design_system.style.gBTypography
import com.gbmultiplatform.design_system.style.gray_box_in_black_bg
import com.gbmultiplatform.domain.utils.CommonImage
import com.gbmultiplatform.domain.utils.SharedImagesBridge
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
    commonImage: CommonImage?,
    iconSize: Dp = 32.dp,
    onClick: () -> Unit = {},
) {
    var showZoomDialog by remember { mutableStateOf(false) }

    val imageLoader = SharedImagesBridge()
    val uri = commonImage?.uri
    val imageState = remember(uri) { mutableStateOf<ByteArray?>(null) }
    LaunchedEffect(uri) {
        imageState.value = if (uri != null) {
            imageLoader.loadImage(uri, maxWidth = 256, maxHeight = 256, quality = 80)
        } else {
            null
        }
    }

    val image = imageState.value

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
                    .clickable { showZoomDialog = true }
                    .padding(12.dp)
                    .size(iconSize),
                image = image
            )
        }
    }

    if (showZoomDialog && image != null) {
        ZoomableImage(
            image = image,
            dismiss = { showZoomDialog = false }
        )
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
                modifier = Modifier.height(400.dp),
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
