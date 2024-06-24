package com.mediaan.mymediaan.view.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mediaan.mymediaan.ui.theme.MediaanPrimary
import kotlinx.coroutines.launch

@Composable
fun RippleEffectButton(modifier: Modifier = Modifier, text: String = "Click me!", onAnimationEnd: () -> Unit) {
    val animationScope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }

    Box(
        modifier = modifier
            .background(MediaanPrimary, shape = RoundedCornerShape(8.dp))
            .clickable {
                animationScope.launch {
                    scale.animateTo(
                        targetValue = 1.5f,
                        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
                    )
                    scale.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
                    )
                    onAnimationEnd()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            fontSize = (24.sp * scale.value),
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }
}