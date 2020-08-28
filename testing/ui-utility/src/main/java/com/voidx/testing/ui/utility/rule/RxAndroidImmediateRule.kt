package com.voidx.testing.ui.utility.rule

import androidx.annotation.NonNull
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.TimeUnit

class RxAndroidImmediateRule : TestRule {

    val immediate = object : Scheduler() {

        override fun scheduleDirect(
            @NonNull run: Runnable,
            delay: Long,
            @NonNull unit: TimeUnit
        ): Disposable {
            // this prevents StackOverflowErrors when scheduling with a delay
            return super.scheduleDirect(run, 0, unit)
        }

        override fun createWorker(): Worker {
            return ExecutorScheduler.ExecutorWorker({ it.run() }, false, false)
        }
    }

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {

            override fun evaluate() {
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

                try {
                    base?.evaluate()
                } finally {
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}