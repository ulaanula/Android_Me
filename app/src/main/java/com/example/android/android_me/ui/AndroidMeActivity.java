/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    public static String CURRENT_TAG = "body_part";

    private int headindex;
    private int bodyindex;
    private int legindex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);


        if(savedInstanceState==null){

        headindex = getIntent().getExtras().getInt("headIndex");
        bodyindex =  getIntent().getExtras().getInt("bodyIndex");
        legindex =  getIntent().getExtras().getInt("legIndex");

            BodyPartFragment headFragment = new BodyPartFragment();

        headFragment.setImageIds(AndroidImageAssets.getHeads());
        headFragment.setListIndex(headindex);
        //Use fragmentManager and transaction to add the fragment to the screen
        FragmentManager fragmentManager =  getSupportFragmentManager();

        //fragment transaction
        fragmentManager.beginTransaction()
                .add(R.id.head_container, headFragment)
                .commit();

        BodyPartFragment bodyFragment = new BodyPartFragment();

        bodyFragment.setImageIds(AndroidImageAssets.getBodies());
        bodyFragment.setListIndex(bodyindex);

        fragmentManager.beginTransaction()
                .add(R.id.body_container, bodyFragment)
                .commit();

        BodyPartFragment legsFragment = new BodyPartFragment();

        legsFragment.setImageIds(AndroidImageAssets.getLegs());
        legsFragment.setListIndex(legindex);
        fragmentManager.beginTransaction()
                .add(R.id.legs_container, legsFragment)
                .commit();

        }
    }

}
