package com.indocyber.ui

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.NavDirections
import com.indocyber.ext.SingleLiveEvent

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val navigationtEvent = SingleLiveEvent<NavDirections>()
    var parent:BaseViewModel? = null
    fun navigate(nav: NavDirections) {
        navigationtEvent.postValue(nav)
    }
}