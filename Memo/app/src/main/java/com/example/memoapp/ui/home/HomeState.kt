package com.example.memoapp.ui.home

import android.content.Intent
import com.example.memoapp.ui.content.ContentActivity

class HomeState(val activity: HomeActivity) {
    fun showContent(index: Int) {
        activity.startActivity(Intent(activity, ContentActivity::class.java).apply {
            putExtra("id", index)
        })
    }
}