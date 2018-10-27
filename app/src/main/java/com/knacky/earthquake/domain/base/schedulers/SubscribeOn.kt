package com.knacky.earthquake.domain.base.schedulers

import io.reactivex.Scheduler


interface SubscribeOn {

    val scheduler: Scheduler

}