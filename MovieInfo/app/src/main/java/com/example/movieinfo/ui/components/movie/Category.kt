package com.example.movieinfo.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movieinfo.features.common.entity.CategoryEntity
import com.example.movieinfo.features.feed.presentation.input.IFeedViewModelInput
import com.example.movieinfo.ui.theme.Paddings


@Composable
fun CategoryRow(
    categoryEntity: CategoryEntity,
    input: IFeedViewModelInput
){
    Column() {
        CategoryTitle( categoryEntity.genre)
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = Paddings.large
            ),
        ){
            // 실제 데이터가 들어갔을때 item() -> itemIndexed()
           itemsIndexed(categoryEntity.movieFeedEntities){ _, item ->
               MovieItem(
                    movie = item ,
                   input = input
               )
           }
        }
    }
}

@Composable
fun CategoryTitle(genre: String) {
    Text(
        text = genre,
        modifier = Modifier.padding(Paddings.large),
        style = MaterialTheme.typography.headlineLarge
    )
}

//@Preview
//@Composable
//fun CategoryPreview(){
//    MovieInfoTheme {
//        //CategoryRow()
//    }
//}
