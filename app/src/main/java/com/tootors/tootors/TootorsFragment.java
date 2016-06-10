package com.tootors.tootors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

    public static  List<Tootor> tootors;

    private ArrayAdapter<String> mTootorsAdapter;

    public TootorsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         ArrayList<String> list =  new ArrayList<String>();

        mTootorsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_tootors,
                R.id.list_item_tooters_textview, list)
            {
                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                {
                    View row;

                    if (null == convertView) {
                        row = inflater.inflate(R.layout.list_item_tootors, null);
                    } else {
                        row = convertView;
                    }
                    TextView tv = (TextView) row.findViewById(R.id.list_item_tooters_textview);
                    tv.setText(Html.fromHtml(getItem(position)));
                    return row;
                }

            };

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

        if (min == 0) {
            return new String[]{"No results found"};
        }

        for (int i = 0; i < min; i++) {

            String name;
            String city;
            String focus;



            name = tootors.get(i).getName();
            city = tootors.get(i).getCity();
            focus = tootors.get(i).getFocus();

            resultStrs[i] =
                    "<font color=\"black\"><b>Name: </b></font><b><font color=\"#0097d8\">" + name + "</font></b><br><small>" +
                    "<font color=\"black\"><b>City:</b></font> " + city +"<br>"+
                    "<font color=\"black\"><b>Focus:</b></font> " + focus+"<small>";
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
        mTootorsAdapter.addAll(updateListView(9));
    }

    public AdapterView.OnItemClickListener listItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (tootors.size() == 0) return;

            Intent profile = new Intent(getContext(), ProfileActivity.class);
            String json = new Gson().toJson(tootors.get(position));
            profile.putExtra(ProfileActivity.EXTRA, json);
            startActivity(profile);
        }
    };

}
