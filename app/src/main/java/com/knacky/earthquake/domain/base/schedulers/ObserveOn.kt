package com.knacky.earthquake.domain.base.schedulers

import io.reactivex.Scheduler

interface ObserveOn {

    val scheduler: Scheduler

}