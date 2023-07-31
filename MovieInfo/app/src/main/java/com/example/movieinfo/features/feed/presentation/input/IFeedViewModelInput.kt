package com.example.movieinfo.features.feed.presentation.input

interface IFeedViewModelInput {

    fun openDetail(movieName: String)
    fun openInfoDialog()
    //viewModel에서 사용
    fun refreshFeed()
}