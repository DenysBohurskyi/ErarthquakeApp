package com.knacky.earthquake.domain.base

import io.reactivex.observers.DisposableObserver

open class DefaultSubscriber<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {}

    override fun onComplete() {}

    override fun onError(e: Throwable) {}
}