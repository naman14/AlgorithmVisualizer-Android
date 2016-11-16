/*
 * Copyright (C) 2016 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.naman14.algovisualizer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.naman14.algovisualizer.algorithm.Algorithm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class AlgoDescriptionFragment extends Fragment {

    LinearLayout rootView;
    String descJson;
    JSONObject descObject;

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
        View view = inflater.inflate(R.layout.fragment_algo_description, container, false);

        rootView = (LinearLayout) view.findViewById(R.id.rootView);
        setCodeDesc(getArguments().getString(Algorithm.KEY_ALGORITHM));

        return view;
    }

    public void setCodeDesc(final String key) {
        if (descJson == null && getActivity() != null) {
            new AsyncTask<Void, String, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    return DataUtils.readDescJson(getActivity());
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    descJson = s;
                    try {
                        descObject = new JSONObject(descJson);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    addDescData(key);
                }
            }.execute();
        } else addDescData(key);
    }

    private void addDescData(String algorithmKey) {
        if (descJson == null || descObject == null || getActivity() == null) {
            return;
        }
        rootView.removeAllViews();
        try {
            JSONObject dataObject = descObject.getJSONObject(algorithmKey);

            Iterator<?> keys = dataObject.keys();

            while (keys.hasNext()) {

                View descView = LayoutInflater.from(getActivity()).inflate(R.layout.item_code_desc, rootView, false);
                TextView title = (TextView) descView.findViewById(R.id.title);
                TextView desc = (TextView) descView.findViewById(R.id.desc);
                desc.setMovementMethod(LinkMovementMethod.getInstance());

                String key = (String) keys.next();
                title.setText(key);

                if (dataObject.get(key) instanceof JSONObject) {
                    JSONObject jsonObject = dataObject.getJSONObject(key);
                    String descString = "";

                    Iterator<?> complexityKeys = jsonObject.keys();

                    while (complexityKeys.hasNext()) {
                        String complexityKey = (String) complexityKeys.next();
                        descString += " - ";
                        descString += complexityKey;
                        descString += " : ";
                        descString += jsonObject.getString(complexityKey);
                        descString += "<br>";
                    }
                    desc.setText(Html.fromHtml(descString));

                } else if (dataObject.get(key) instanceof JSONArray) {
                    JSONArray array = dataObject.getJSONArray(key);
                    String descString = "";

                    for (int i = 0; i < array.length(); i++) {
                        descString += " - ";
                        descString += array.getString(i);
                        descString += "<br>";
                    }
                    desc.setText(Html.fromHtml(descString));

                } else if (dataObject.get(key) instanceof String) {
                    desc.setText(Html.fromHtml(dataObject.getString(key)));
                }

                rootView.addView(descView);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
