package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by Anna on 16.05.2017.
 */
// This activity is responsible for displaying the master list of all images
// Implements the MasterListFragment callback, OnImageClickListener
public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    // Variables to store the values for the list index of the selected images
    // The default value will be index = 0
    private int headIndex;
    private int bodyIndex;
    private int legindex;

    // Create variable to track whether to display a two-pane or single-pane layout
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Also for two-pane layout get rid of the "next" button in master list fragment
        // determine of you are creating a two-pane or single-pane display
        if(findViewById(R.id.android_me_linear_layout)!=null){
            mTwoPane= true;

            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            // Change a GridView to space out the images more on tablet
            GridView gridView = (GridView)findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);


            //Create new body part fragments
            if(savedInstanceState==null){


                //Use fragmentManager and transaction to add the fragment to the screen
                FragmentManager fragmentManager =  getSupportFragmentManager();

                // head Fragment
                BodyPartFragment headFragment = new BodyPartFragment();

                headFragment.setImageIds(AndroidImageAssets.getHeads());


                //fragment transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // body Fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();

                bodyFragment.setImageIds(AndroidImageAssets.getBodies());


                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                // legs Fragment
                BodyPartFragment legsFragment = new BodyPartFragment();

                legsFragment.setImageIds(AndroidImageAssets.getLegs());
                legsFragment.setListIndex(legindex);
                fragmentManager.beginTransaction()
                        .add(R.id.legs_container, legsFragment)
                        .commit();

            }


        }else{
            mTwoPane = false;
        }

    }
    // Define the behavior for onImageSelected; create a Toast that displays the position clicked
    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position clicked =  " + position, Toast.LENGTH_SHORT).show();

        // based od where the user has clicked, store the selected list index for the head, body and legs
        // bodyPartNumber will be = 0 for the head fragment, 1 for the body, 2 for the leg fragment
        // dividing by 12 gives us these integer values because each list of images resources has 12 images
        int bodyPartNumber = position/12;

        // Store the correct list index no matter where in the image list has been clicked
        // This ensures that the index will always be a value between 0-11
        int listindex = position - bodyPartNumber*12;


        if(mTwoPane){

            BodyPartFragment newFragment = new BodyPartFragment();

            switch (bodyPartNumber){
                case 0 :

                    // A head image has been clicked
                    // Give the correct image resource to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listindex);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;

                case 1 :

                    // A body image has been clicked
                    // Give the correct image resource to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listindex);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;

                case 2 :

                    // A legs image has been clicked
                    // Give the correct image resource to the new fragment
                    newFragment.setImageIds(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listindex);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.legs_container, newFragment)
                            .commit();
                    break;

            }

        }else {

            // set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listindex;
                    break;
                case 1:
                    bodyIndex = listindex;
                    break;
                case 2:
                    legindex = listindex;
                    break;
                default:
                    break;
            }
        }

        // Put this information in Bundle
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legindex);

        // and attach it to an Intent that will launch AndroidMe Activity
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(b);

        // TODO Get a reference to the "Next" button and launch the intent when this button is clicked

        Button nextButton = (Button)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
