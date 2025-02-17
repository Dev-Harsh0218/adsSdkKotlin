package com.example.adsdk.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.adsdk.domain.models.adModels.popup.PopupAdData
import com.example.adsdk.presentation.state.PopupAdState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class PopupViewModel: ViewModel() {
    private val _popupAdState = MutableStateFlow(PopupAdState())
    val popupAdState: StateFlow<PopupAdState> = _popupAdState.asStateFlow()

    fun updateState(isLoading:Boolean= false, popupAdData: PopupAdData? = null, error: String? = null){
        _popupAdState.value = PopupAdState(
            isLoading = isLoading,
            popupAdData = popupAdData,
            error = error
        )
    }
}