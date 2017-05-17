package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by Anna on 16.05.2017.
 */

// This fragment displays all of the AndroidMe images in one large list
// The list appears as a grid of images
public class MasterListFragment extends Fragment {

    private MasterListAdapter mAdapter;

    //Tag für Logging
    public static final String TAG = "MasterListFragment";


    // TODO (1) Define a new interface OnImageClickListener that triggers a callback in the host activity
            // The callback is a method names onImageSelected(int position) that contains information about
            // which position on the grid of images a user has clicked

    OnImageClickListener mCallback;

    // OnImageClickListener interface, calls a method in the host activity named onImageSelected
    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    // TODO (2) Override onAttach to make sure that the container activity has implemented the callback


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception

        try{
            mCallback = (OnImageClickListener)context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()
                        + " must implement onImageClickListener");
        }


    }

    // mandatory empty constructor
    public MasterListFragment(){

    }

    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {


        final View rootView =  inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        //Create the adapter
        // this adapter takes in the context and an ArrayList of All th image resources to display
        mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //set adapter
        gridView.setAdapter(mAdapter);

        //TODO (3) Set a click listener on the gridView and trigger callback onImageSelected when an item is selected
        // Set a click listener on the gridView and trigger callback onImageSelected when an item is selected
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //trigger the callback method and pass in the position that was clicked
                mCallback.onImageSelected(position);
            }
        });


        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {


    }

}
