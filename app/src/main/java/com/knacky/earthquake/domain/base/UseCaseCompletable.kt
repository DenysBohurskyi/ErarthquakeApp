package com.knacky.earthquake.domain.base

import com.knacky.earthquake.domain.base.UseCase
import com.knacky.earthquake.domain.base.schedulers.ObserveOn
import com.knacky.earthquake.domain.base.schedulers.SubscribeOn
import io.reactivex.Completable

abstract class UseCaseCompletable(subscribeOn: SubscribeOn, observeOn: ObserveOn) : UseCase(subscribeOn, observeOn) {

    protected var completable: Completable? = null

    fun executeCompletable(onComplete: () -> Unit, onError: (Throwable) -> Unit) {
        if (completable == null) {
            completable = useCaseCompletable
                    .subscribeOn(subscribeOn.scheduler)
                    .observeOn(observeOn.scheduler)
                    .doOnError { completable = null }
                    .doOnComplete { completable = null }
        }
        subscription = completable!!.subscribe({ onComplete() }, { onError(it) })
    }

    protected abstract val useCaseCompletable: Completable
}