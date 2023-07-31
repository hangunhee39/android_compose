package com.example.movieinfo.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections

//유저가 계속해서 눌렀을때 stack 이 쌓인는 것을 방지
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}