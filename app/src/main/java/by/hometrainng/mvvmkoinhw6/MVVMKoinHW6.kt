package by.hometrainng.mvvmkoinhw6

import android.app.Application
import by.hometrainng.mvvmkoinhw6.koin.databaseModule
import by.hometrainng.mvvmkoinhw6.koin.networkModule
import by.hometrainng.mvvmkoinhw6.koin.repositoryModule
import by.hometrainng.mvvmkoinhw6.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MVVMKoinHW6: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MVVMKoinHW6)
            modules(
                databaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}
