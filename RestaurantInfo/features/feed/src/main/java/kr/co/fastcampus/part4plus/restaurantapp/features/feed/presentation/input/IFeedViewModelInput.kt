package kr.co.fastcampus.part4plus.restaurantapp.features.feed.presentation.input

interface IFeedViewModelInput {
    fun openDetail(id: Int)
    fun openInfoDialog()
    fun refreshFeed()
}
