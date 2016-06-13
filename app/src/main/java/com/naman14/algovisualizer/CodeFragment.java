package com.naman14.algovisualizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.naman14.algovisualizer.algorithm.Algorithm;

/**
 * Created by naman on 03/06/16.
 */
public class CodeFragment extends Fragment {

    public static CodeFragment newInstance(String algorithm) {
        CodeFragment fragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Algorithm.KEY_ALGORITHM, algorithm);
        fragment.setArguments(bundle);
        return fragment;
    }
}
