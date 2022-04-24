package com.alexmumo.car.util

import androidx.lifecycle.Observer

class Event<out T>(private val content: T) {
    var beingHandled = false
        private set

    fun fetchContentIfNotHandled(): T? {
        return if (!beingHandled) {
            beingHandled = true
            content
        } else null
    }
    fun peekContent() = content
}

class EventObserver<T>(
    private inline val onError: ((String) -> Unit)? = null,
    private inline val onLoading: (() -> Unit)? = null,
    private inline val onSuccess: (T) -> Unit
) : Observer<Event<Resource<T>>> {

    override fun onChanged(t: Event<Resource<T>>?) {
        when (val content = t?.peekContent()) {
            is Resource.Success -> {
                content.data?.let(onSuccess)
            }
            is Resource.Error -> {
                t.fetchContentIfNotHandled().let {
                    onError?.let { error ->
                        error(it?.message!!)
                    }
                }
            }

            is Resource.Loading -> {
                onLoading?.let { loading ->
                    loading()
                }
            }
        }
    }

}

