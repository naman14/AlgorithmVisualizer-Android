package com.naman14.algovisualizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naman14.algovisualizer.algorithm.Algorithm;

/**
 * Created by naman on 03/06/16.
 */
public class AlgoDescriptionFragment extends Fragment {

    public static AlgoDescriptionFragment newInstance(String algorithm) {
        AlgoDescriptionFragment fragment = new AlgoDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Algorithm.KEY_ALGORITHM, algorithm);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_algo_description, container, false);

        setCodeDesc(getArguments().getString(Algorithm.KEY_ALGORITHM));
        return rootView;
    }

    public void setCodeDesc(String key) {

    }
}
