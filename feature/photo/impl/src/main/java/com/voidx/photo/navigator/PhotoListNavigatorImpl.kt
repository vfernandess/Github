package com.voidx.photo.navigator

import com.voidx.github.core.navigator.Navigator
import com.voidx.photo.ui.view.PhotoListFragment
import javax.inject.Inject

class PhotoListNavigatorImpl @Inject constructor (
    private val navigation: Navigator
) : PhotoListNavigator {

    override fun showPhotoList() {
        navigation.navigateTo(PhotoListFragment())
    }
}
