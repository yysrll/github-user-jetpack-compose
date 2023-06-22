package com.yusril.githubusers.ui.page.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusril.githubusers.data.repository.UserRepository
import com.yusril.githubusers.data.repository.UserRepositoryImpl
import com.yusril.githubusers.model.User
import com.yusril.githubusers.vo.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository = UserRepositoryImpl.getInstance(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private var job: Job? = null

    private val _users = MutableStateFlow<Resource<List<User>>>(Resource.empty())
    val users: StateFlow<Resource<List<User>>> = _users

    fun searchUsers(q: String) {
        _users.value = Resource.loading()
        job?.cancel()
        job = viewModelScope.launch(dispatcher) {
            _users.value = repository.searchUsers(q)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}