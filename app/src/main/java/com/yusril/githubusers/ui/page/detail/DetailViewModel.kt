package com.yusril.githubusers.ui.page.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusril.githubusers.data.repository.UserRepository
import com.yusril.githubusers.data.repository.UserRepositoryImpl
import com.yusril.githubusers.model.UserDetail
import com.yusril.githubusers.vo.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: UserRepository = UserRepositoryImpl.getInstance(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _detailUser = MutableStateFlow<Resource<UserDetail>>(Resource.loading())
    val detailUser: StateFlow<Resource<UserDetail>> = _detailUser

    fun getDetailUser(username: String) = viewModelScope.launch(dispatcher) {
        _detailUser.value = repository.getDetailUser(username)
    }
}