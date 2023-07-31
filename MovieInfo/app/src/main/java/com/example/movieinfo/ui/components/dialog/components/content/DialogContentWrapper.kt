package com.example.movieinfo.ui.components.dialog.components.content

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.em
import com.example.movieinfo.ui.models.dialog.DialogContent
import com.example.movieinfo.ui.models.dialog.DialogText
import com.example.movieinfo.ui.theme.Paddings

//local Style 적용
data class DialogContentStyle(
    val textStyle: @Composable () -> TextStyle = { MaterialTheme.typography.bodyMedium },
    val contentTopPadding: Dp = Paddings.xlarge,
    val contentBottomPadding: Dp = Paddings.extra
)

val LocalDialogContentStyle = compositionLocalOf { DialogContentStyle() }

@Composable
fun ColumnScope.DialogContentWrapper(content: DialogContent) {
    when (content) {
        is DialogContent.Default -> {
            CompositionLocalProvider(
                LocalDialogContentStyle provides DialogContentStyle(
                    textStyle = {
                        MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 1.6.em
                        )
                    },
                    contentBottomPadding = Paddings.extra,
                    contentTopPadding = Paddings.extra
                )
            ) {
                NormalTextContent(content.dialogText)
            }
        }

        is DialogContent.Large -> {
            CompositionLocalProvider(
                LocalDialogContentStyle provides DialogContentStyle(
                    textStyle = {
                        MaterialTheme.typography.headlineSmall.copy(
                            lineHeight = 1.6.em
                        )
                    },
                    contentBottomPadding = Paddings.extra,
                    contentTopPadding = Paddings.extra
                )
            ) {
                NormalTextContent(content.dialogText)
            }
        }

        is DialogContent.Rating -> {
            RatingContent(content.dialogText)
        }
    }
}



@Composable
fun ColumnScope.NormalTextContent(text: DialogText.Default) {
    Text(
        text = getStringFromDialogText(text),
        modifier = Modifier
            .padding(
                top = LocalDialogContentStyle.current.contentTopPadding,
                bottom = LocalDialogContentStyle.current.contentBottomPadding
            )
            //ColumnScope의 확장함수로 만들어야 한다!!
            .align(Alignment.CenterHorizontally),
        textAlign = TextAlign.Center,
        style = LocalDialogContentStyle.current.textStyle.invoke()
    )
}

@Composable
//text와 id 둘 중의 어느 하나가 null인지 확인하는 함수
fun getStringFromDialogText(text: DialogText.Default): String {
    return text.id?.let {
        stringResource(id = it)
    }?: text.text ?:""
}