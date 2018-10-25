/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigationsample

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import io.github.erikcaffrey.arch_components_paging_library.di.Injection
import io.github.erikcaffrey.arch_components_paging_library.view.MoviesActivity
import io.github.erikcaffrey.arch_components_paging_library.view.MoviesPagedListAdapter
import io.github.erikcaffrey.arch_components_paging_library.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_movies.*


/**
 * Shows a register form (that does nothing, for simplicity)
 */
class MoviesList : Fragment() {
    private lateinit var moviesViewModel: MoviesViewModel
    private val moviePagedListAdapter = MoviesPagedListAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_movies, container, false)


        return view
    }

    override fun onResume() {
        super.onResume()

        initRecycler()
        initViewModel()
        initAdapter()
    }
    private fun initRecycler() {
        val linearLayoutManager = GridLayoutManager(context, 2)
        view!!.findViewById<RecyclerView>(R.id.movies_recycler).layoutManager = linearLayoutManager
    }

    private fun initViewModel() {
        moviesViewModel = Injection.provideMoviesViewModel(this)
        moviesViewModel.getMovies()
    }

    private fun initAdapter() {
        view!!.findViewById<RecyclerView>(R.id.movies_recycler).adapter = moviePagedListAdapter
        moviesViewModel.pagedListMovie.observe(this, Observer<PagedList<Movie>> {
            Log.d(MoviesActivity::class::java.name, "Movies: ${it?.size}")
            moviePagedListAdapter.submitList(it)
        })
    }

}
