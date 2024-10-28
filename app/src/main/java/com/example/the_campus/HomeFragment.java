package com.example.the_campus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    RecyclerView newsFeedRecyclerview;
    NewsFeedAdapter newsFeedAdapter;
    ArrayList<NewsFeedModelClass> arrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        newsFeedRecyclerview = v.findViewById(R.id.newsFeedRecyclerview);


        newsFeedAdapter = new NewsFeedAdapter(getContext());
        newsFeedRecyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsFeedRecyclerview.setLayoutManager(linearLayoutManager);
        newsFeedRecyclerview.setAdapter(newsFeedAdapter);

        return v;
    }
}