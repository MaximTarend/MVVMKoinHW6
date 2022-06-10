package by.hometrainng.mvvmkoinhw6.koin

import by.hometrainng.mvvmkoinhw6.viewModels.DetailsViewModel
import by.hometrainng.mvvmkoinhw6.viewModels.ListViewModel
import by.hometrainng.mvvmkoinhw6.viewModels.RandomBeerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
//    viewModel { ListViewModel(get(), get(), get()) }
    viewModelOf(::ListViewModel)

    viewModel { (id: Int) -> DetailsViewModel(id, get()) }
//    viewModelOf(::DetailsViewModel) - это сработает так же?

    viewModelOf(::RandomBeerViewModel)
//    viewModel { RandomBeerViewModel(get()) }
}