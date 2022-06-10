package by.hometrainng.mvvmkoinhw6

import android.app.Application
import by.hometrainng.mvvmkoin6.data.koin.dataModule
import by.hometrainng.mvvmkoin6.data.koin.sharedPrefsModule
import by.hometrainng.mvvmkoinhw6.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MVVMKoinHW6: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MVVMKoinHW6)
            modules(
                dataModule,
                viewModelModule,
                sharedPrefsModule
            )
        }
    }
}
