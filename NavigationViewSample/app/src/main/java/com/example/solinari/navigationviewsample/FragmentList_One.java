package com.example.solinari.navigationviewsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Solinari on 2016/12/31.
 */

public class FragmentList_One extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private String[] Dataset;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentlist_one, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        setRecyclerView();
        return view;
    }
    public void setRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setData();
        recyclerAdapter = new RecyclerViewAdapter(Dataset);
        recyclerView.setAdapter(recyclerAdapter);

    }
    public void setData(){
      Dataset = new String[21];
        for (int i = 0 ; i < 21;i++){
           Dataset[i] = Integer.toString(i);
        }

    }
}
