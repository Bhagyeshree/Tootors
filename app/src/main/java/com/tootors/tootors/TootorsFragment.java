package com.tootors.tootors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TootorsFragment extends Fragment {

    private ArrayAdapter<String> mTootorsAdapter;

    public TootorsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTootorsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_tootors,
                R.id.list_item_tooters_textview, new ArrayList<String>());

        View rootView = inflater.inflate(R.layout.fragment_main_tootors, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listview_tootors);

        listView.setAdapter(mTootorsAdapter);

        return rootView;
    }

    private void updateTootors() {

        FetchTootorTask tootorTask = new FetchTootorTask();

        tootorTask.execute();
    }

    @Override
    public void onStart() {
        super.onStart();

        updateTootors();
    }
}
