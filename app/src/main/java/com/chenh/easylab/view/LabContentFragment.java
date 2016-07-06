package com.chenh.easylab.view;

/**
 * Created by chenh on 2016/7/2.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chenh.easylab.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LabContentFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public LabContentFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LabContentFragment newInstance(int sectionNumber) {
        LabContentFragment fragment;
        switch (sectionNumber){
            case 1:
                fragment = new LabOverViewFragment();
                break;
            case 2:
                fragment = new LabFlowFragment();
                break;
            case 3:
                fragment = new LabTableFragment();
                break;
            default:
                fragment = new LabContentFragment();
        }


        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lab_content, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}

