package by.hometrainng.mvvmkoinhw6.koin

import by.hometrainng.mvvmkoinhw6.viewModels.DetailsViewModel
import by.hometrainng.mvvmkoinhw6.viewModels.ListViewModel
import by.hometrainng.mvvmkoinhw6.viewModels.RandomBeerViewModel
import by.hometrainng.mvvmkoinhw6.viewModels.BreweryMapViewModel
import by.hometrainng.mvvmkoinhw6.viewModels.BottomSheetInfoViewModel

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ListViewModel)
//    viewModel { (id: Int) -> DetailsViewModel(id, get()) }
    viewModelOf(::DetailsViewModel)
    viewModelOf(::RandomBeerViewModel)
    viewModelOf(::BreweryMapViewModel)
    viewModelOf(::BottomSheetInfoViewModel)


}