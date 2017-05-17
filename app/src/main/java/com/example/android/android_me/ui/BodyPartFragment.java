package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anna on 28.04.2017.
 */

public class BodyPartFragment extends Fragment {


    private List<Integer> mImageIds;
    private int mListIndex;

    //Tag für Logging
    public static final String TAG = "BodyPartFragment";

    //final Strings to store state information about the list of images and list index
    public static final String IMAGE_ID_LIST= "imageId's";
    public static final String LIST_INDEX= "list_index";

    // Mandatory constructor for instantiating the fragment
    public void BodyPartFragment(){

    }

    /*
    * Inflates the fragment layout and sets any image resources
    */
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        //Load the saved state if there is one ( the list of images and list index)
        if(savedInstanceState!=null){
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
        }


        // Inflate the Android-Me fragment layout
        View bodyPartFragmentView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Get the reference to the imageView in the fragemnet layout
        final ImageView imageView = (ImageView) bodyPartFragmentView.findViewById(R.id.body_part_image_view);


        if(mImageIds!=null){
            //Set the image resource to the list item at the stored index
            imageView.setImageResource(mImageIds.get(mListIndex));
        }else{
            //Log a massage saying the image id list is null
            Log.v(TAG, "This fragment has a null list of image ids");
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListIndex < mImageIds.size()-1){
                    mListIndex++;
                }else{
                    mListIndex =0;
                }

                imageView.setImageResource(mImageIds.get(mListIndex));
            }
        });


        return bodyPartFragmentView;
    }

    public void setImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public void setListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {

        currentState.putIntegerArrayList(IMAGE_ID_LIST,(ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mListIndex);

    }
}
