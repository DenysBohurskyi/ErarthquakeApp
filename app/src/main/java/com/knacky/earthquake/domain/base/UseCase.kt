package com.knacky.earthquake.domain.base

import com.knacky.earthquake.domain.base.schedulers.ObserveOn
import com.knacky.earthquake.domain.base.schedulers.SubscribeOn
import io.reactivex.disposables.Disposables

open class UseCase(protected var subscribeOn: SubscribeOn, protected var observeOn: ObserveOn) {

    protected var subscription = Disposables.empty()

    fun dispose() {
        if (!subscription.isDisposed){
            subscription.dispose()
        }
    }


}