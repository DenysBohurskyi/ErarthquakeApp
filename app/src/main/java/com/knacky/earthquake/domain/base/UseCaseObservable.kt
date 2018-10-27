package com.knacky.earthquake.domain.base

import com.knacky.earthquake.domain.base.schedulers.ObserveOn
import com.knacky.earthquake.domain.base.schedulers.SubscribeOn
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

abstract class UseCaseObservable<T>(subscribeOn: SubscribeOn, observeOn: ObserveOn) : UseCase(subscribeOn, observeOn) {

    private var observable: Observable<T>? = null

    fun executeObservable(subscriber: DisposableObserver<T>) {
        observable = null
        subscription.dispose()

        if (observable == null) {
            observable = useCaseObservable
                    .subscribeOn(subscribeOn.scheduler)
                    .observeOn(observeOn.scheduler)
                    .doOnError { observable = null }
                    .doOnComplete { observable = null }
                    .doOnDispose { observable = null }
        }
        subscription = observable!!.subscribeWith(subscriber)
    }

    protected abstract val useCaseObservable: Observable<T>

}