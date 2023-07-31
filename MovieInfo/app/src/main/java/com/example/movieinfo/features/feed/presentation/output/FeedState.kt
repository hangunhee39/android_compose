package com.example.movieinfo.features.feed.presentation.output

import com.example.movieinfo.features.common.entity.CategoryEntity

sealed class FeedState {
    object Loading : FeedState()
    class Main(
        val categories: List<CategoryEntity>
    ) : FeedState()

    class Failed(
        val reason: String
    ) : FeedState()
}