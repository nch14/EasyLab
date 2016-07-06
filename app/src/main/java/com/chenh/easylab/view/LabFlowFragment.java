package com.chenh.easylab.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chenh.easylab.R;

import java.util.ArrayList;

/**
 * Created by chenh on 2016/7/5.
 */
public class LabFlowFragment extends LabContentFragment {
    private ListView mListView;
    private ArrayList<String> name;
    private ArrayAdapter nameAdapter;

    public static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lab_flow, container, false);


        mListView= (ListView) rootView.findViewById(R.id.people_name);

        name=new ArrayList<>();
        name.add("王晨");
        name.add("蒋玉莹");
        name.add("茆云松");

        nameAdapter =new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,name);
        mListView.setAdapter(nameAdapter);

        return rootView;
    }
}

