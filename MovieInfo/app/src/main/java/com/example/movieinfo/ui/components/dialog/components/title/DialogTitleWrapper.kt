package com.example.movieinfo.ui.components.dialog.components.title

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.movieinfo.ui.models.dialog.DialogTitle
import com.example.movieinfo.ui.theme.Paddings
import com.example.movieinfo.ui.theme.h5Title

@Composable
fun DialogTitleWrapper(title: DialogTitle) {
    when (title) {
        is DialogTitle.Default -> DefaultDialogTitle(title)
        is DialogTitle.Large -> LargeDialogTitle(title)
        is DialogTitle.Header -> HeaderDialogTitle(title)
    }
}

@Composable
fun HeaderDialogTitle(title: DialogTitle.Header) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(Paddings.large),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title.text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
             style =MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.background
            )
        )
    }
}

@Composable
fun LargeDialogTitle(title: DialogTitle.Large) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(Paddings.large),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title.text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style =MaterialTheme.typography.h5Title.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Composable
fun DefaultDialogTitle(title: DialogTitle.Default) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(Paddings.large),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title.text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            style =MaterialTheme.typography.h5Title.copy(
                color = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}
