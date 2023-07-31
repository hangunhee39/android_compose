package com.example.movieinfo.ui.models.dialog

sealed class DialogText {
    //text 랑 id 중 둘중 하나만 사용하기 때문에 파라미터로 x
    abstract var text: String?
    //id 값으로 text 가져오기
    abstract var id: Int?

    class Default() : DialogText() {
        override var text: String? = null
        override var id: Int? =null
        constructor(text: String): this() {
            this.text = text
        }
        constructor(text: Int) :this()  {
            this.id = text
        }
    }

    class Rating() : DialogText() {
        override var text: String? = null
        override var id: Int? = null
        var rating: Float = 0.0f

        constructor(text: String, rating: Float) : this() {
            this.text = text
            this.rating = rating
        }

        constructor(text: Int, rating: Float) : this() {
            this.id = text
            this.rating = rating
        }
    }
}