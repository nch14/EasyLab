package com.chenh.easylab.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenh.easylab.R;

/**
 * Created by chenh on 2016/6/20.
 */
public class MAuthorityManageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_manager_authority_manage,container, false);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("权限管理");
    }

    public static MAuthorityManageFragment newInstance(){
       /* Bundle args=new Bundle();
        //args.putSerializable(EXTRA_CRIME_ID,crimeID);
*/
        MAuthorityManageFragment fragment=new MAuthorityManageFragment();
        //fragment.setArguments(args);

        return fragment;
    }
}
