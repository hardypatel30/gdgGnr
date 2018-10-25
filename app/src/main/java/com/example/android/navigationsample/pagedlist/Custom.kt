package io.github.erikcaffrey.arch_components_paging_library

import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import io.github.erikcaffrey.arch_components_paging_library.data.remote.MoviesRemoteDataSource
import io.github.erikcaffrey.arch_components_paging_library.data.remote.toMovieEntity
import io.github.erikcaffrey.arch_components_paging_library.data.repository.PageListMovieBoundaryCallback
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import io.github.erikcaffrey.arch_components_paging_library.data.room.MoviesRoomDataSource
import io.reactivex.schedulers.Schedulers

class Custom(private val moviesRemoteDataSource: MoviesRemoteDataSource,
             private val moviesRoomDataSource: MoviesRoomDataSource):ItemKeyedDataSource<Int,Movie>(){
    private var isRequestRunning = false
    private var requestedPage = 1


    private fun fetchAndStoreMovies() {
        if (isRequestRunning) return

        isRequestRunning = true
        moviesRemoteDataSource.fetchMovies(requestedPage)
                .map { movieApiList -> movieApiList.map { it.toMovieEntity() } }
                .doOnSuccess { listMovie ->
                    if (listMovie.isNotEmpty()) {

                        moviesRoomDataSource.storeMovies(listMovie)
                        Log.i(PageListMovieBoundaryCallback.TAG, "Inserted: ${listMovie.size}")
                    } else {
                        Log.i(PageListMovieBoundaryCallback.TAG, "No Inserted")
                    }
                    requestedPage++
                }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .toCompletable()
                .doFinally { isRequestRunning = false }
                .subscribe({ Log.i(PageListMovieBoundaryCallback.TAG, "Movies Completed") }, { it.printStackTrace() })

    }
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Movie>) {
        fetchAndStoreMovies()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Movie>) {
        fetchAndStoreMovies()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKey(item: Movie): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}





