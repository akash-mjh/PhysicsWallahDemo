package com.physicswallahdemo

object AppViewModelProvider {
    private val mainViewModel = MainViewModel()

    fun getMainViewModel(): MainViewModel {
        return mainViewModel
    }
}