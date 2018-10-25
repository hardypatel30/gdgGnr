package io.github.erikcaffrey.arch_components_paging_library.view

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.example.android.navigationsample.R
import io.github.erikcaffrey.arch_components_paging_library.data.room.Movie
import io.github.erikcaffrey.arch_components_paging_library.di.Injection
import io.github.erikcaffrey.arch_components_paging_library.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_movies.*

class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)


    }


}
