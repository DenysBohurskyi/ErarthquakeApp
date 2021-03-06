package com.knacky.earthquake.domain.base

import com.knacky.earthquake.domain.base.schedulers.ObserveOn
import com.knacky.earthquake.domain.base.schedulers.SubscribeOn
import io.reactivex.Single

abstract class UseCaseSingle<T>(subscribeOn: SubscribeOn, observeOn: ObserveOn) : UseCase(subscribeOn, observeOn) {

    private var single: Single<T>? = null

    fun executeSingle(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        if (single == null){
            single = useCaseSingle
                    .subscribeOn(subscribeOn.scheduler)
                    .observeOn(observeOn.scheduler)
                    .doOnSuccess { single = null }
                    .doOnError { single = null }
        }
        subscription = single!!.subscribe({ onSuccess(it) }, { onError(it) })
    }

    protected abstract val useCaseSingle: Single<T>
}