package com.naman14.algovisualizer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class LogFragment extends Fragment {

    LogAdapter adapter;
    RecyclerView recyclerView;
    View emptyView;

    public static LogFragment newInstance() {
        LogFragment fragment = new LogFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_log, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.logrecyclerview);
        emptyView = rootView.findViewById(R.id.empty_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        adapter = new LogAdapter(new ArrayList<String>());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void addLog(final String log) {
        emptyView.setVisibility(View.GONE);
        adapter.addLog(log);
    }

    public void clearLog() {
        if (adapter != null)
            adapter.clearLog();
        if (emptyView != null)
            emptyView.setVisibility(View.VISIBLE);
    }

    private class LogAdapter extends RecyclerView.Adapter<LogAdapter.ItemHolder> {
        public List<String> logList;

        public LogAdapter(List<String> list) {
            this.logList = list;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_log_item, viewGroup, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder itemHolder, int i) {
            itemHolder.logText.setText(logList.get(i));
        }

        @Override
        public int getItemCount() {
            return (null != logList ? logList.size() : 0);
        }

        public class ItemHolder extends RecyclerView.ViewHolder {

            TextView logText;

            public ItemHolder(View view) {
                super(view);
                logText = (TextView) view.findViewById(R.id.text);
            }

        }

        public void addLog(String log) {
            logList.add(log);
            notifyDataSetChanged();
            recyclerView.scrollToPosition(logList.size() - 1);
        }

        public void clearLog() {
            logList.clear();
            notifyDataSetChanged();
        }

    }


}
