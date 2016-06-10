package com.tootors.tootors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tootors.tootors.client.model.Tootor;
import com.tootors.tootors.register.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TootorsFragment extends Fragment {

    private List<Tootor> tootors;

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
        listView.setOnItemClickListener(listItemClick);


        return rootView;
    }

    private void updateTootors() {
        // todo: get location from device
//        String location = "90001";
        
        FetchTootorTask tootorTask = new FetchTootorTask(this);

//        tootorTask.execute(location);
        tootorTask.execute();
    }

    private String[] updateListView(int numTootor) {

        String[] resultStrs = new String[numTootor];

        int min = Math.min(numTootor, tootors.size());
        for (int i = 0; i < min; i++) {

            String name;
            String city;
            String focus;

            name = tootors.get(i).getName();
            city = tootors.get(i).getCity();
            focus = tootors.get(i).getFocus();

            resultStrs[i] = "Name: " + name + " - City: " + city + " - Focus: " + focus;
        }

        return resultStrs;
    }

    @Override
    public void onStart() {
        super.onStart();

        updateTootors();
    }

    public void setTootors(List<Tootor> tootors) {
        this.tootors = tootors;
    }

    public void updateListView() {
        mTootorsAdapter.clear();
        mTootorsAdapter.addAll(updateListView(20));
    }

    public AdapterView.OnItemClickListener listItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent profile = new Intent(getContext(), ProfileActivity.class);
            String json = new Gson().toJson(tootors.get(position));
            profile.putExtra(ProfileActivity.EXTRA, json);
            startActivity(profile);
        }
    };

}
