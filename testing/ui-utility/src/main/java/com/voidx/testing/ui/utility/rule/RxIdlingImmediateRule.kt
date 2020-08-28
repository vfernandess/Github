package com.voidx.testing.ui.utility.rule

import com.squareup.rx3.idler.Rx3Idler
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxIdlingImmediateRule: TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler(Rx3Idler.create("Rxjava 3.0 Io Scheduler"))
                RxJavaPlugins.setInitComputationSchedulerHandler(Rx3Idler.create("Rxjava 3.0 computation Scheduler"))
                RxJavaPlugins.setInitNewThreadSchedulerHandler(Rx3Idler.create("Rxjava 3.0 new thread Scheduler"))
                RxJavaPlugins.setInitSingleSchedulerHandler(Rx3Idler.create("Rxjava 3.0 single scheduler Scheduler"))
//                RxAndroidPlugins.setInitMainThreadSchedulerHandler(Rx3Idler.create("rxjava 3.0 mainscheduler"))
                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}