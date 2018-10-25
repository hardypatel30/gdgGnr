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

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController


/**
 * An activity that inflates a layout that has a NavHostFragment.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateUpToFromChild(child: Activity?, upIntent: Intent?): Boolean {
        if (Navigation.findNavController(this,R.id.my_nav_host_fragment)
                        .getCurrentDestination().getId() == R.id.leaderboard) {
            // handle back button the way you want here
            return false;
        }
        else {
            return findNavController(this, R.id.my_nav_host_fragment)
                    .navigateUp()

        }
    }

    override fun onBackPressed() {
        if (Navigation.findNavController(this,R.id.my_nav_host_fragment)
                        .getCurrentDestination().getId() == R.id.leaderboard) {
            // handle back button the way you want here
            Navigation.findNavController(this,R.id.my_nav_host_fragment).navigate(R.id.back)
        }
        else {
            super.onBackPressed()

        }

    }


}
