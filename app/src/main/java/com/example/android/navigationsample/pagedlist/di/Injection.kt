package io.github.erikcaffrey.arch_components_paging_library.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import io.github.erikcaffrey.arch_components_paging_library.data.remote.MoviesRemoteDataSource
import io.github.erikcaffrey.arch_components_paging_library.data.remote.getService
import io.github.erikcaffrey.arch_components_paging_library.data.repository.MoviesRepository
import io.github.erikcaffrey.arch_components_paging_library.data.room.MoviesDatabase
import io.github.erikcaffrey.arch_components_paging_library.data.room.MoviesRoomDataSource
import io.github.erikcaffrey.arch_components_paging_library.viewmodel.MoviesViewModel
import io.github.erikcaffrey.arch_components_paging_library.viewmodel.MoviesViewModelFactory

object Injection {

    fun provideMoviesViewModel(appCompatActivity: Fragment) =
            ViewModelProviders.of(appCompatActivity, provideMoviesViewModelFactory(appCompatActivity)).get(MoviesViewModel::class.java)

    private fun provideMoviesViewModelFactory(context: Fragment): ViewModelProvider.Factory {
        return MoviesViewModelFactory(provideMoviesRepository(context))
    }

    private fun provideMoviesRepository(context: Fragment) =
            MoviesRepository(MoviesRemoteDataSource(getService()), provideMoviesDatabase(context))

    private fun provideMoviesDatabase(contexty: Fragment): MoviesRoomDataSource {
        val moviesDatabase = MoviesDatabase.getInstance(contexty.context!!)
        return MoviesRoomDataSource(moviesDatabase.movieDao())
    }
}
