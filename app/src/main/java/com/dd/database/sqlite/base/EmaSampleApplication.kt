package com.dd.database.sqlite.base

import com.carmabs.ema.android.base.EmaApplication
import com.dd.database.sqlite.injection.appInjection
import com.dd.injection.appDataInjection
import org.kodein.di.Kodein


class EmaSampleApplication : EmaApplication() {
    override fun injectAppModule(kodein: Kodein.MainBuilder): Kodein.Module? {
        kodein.import(appDataInjection())
        return appInjection(this)
    }
}