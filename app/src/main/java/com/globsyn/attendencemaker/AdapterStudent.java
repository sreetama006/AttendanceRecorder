package com.globsyn.attendencemaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

    public class AdapterStudent extends BaseAdapter {
        Context context;
        ArrayList<Student> mlist;

        public AdapterStudent(Context context, ArrayList<Student> list) {
            this.context=context;
            this.mlist=list;
        }

        public void setData(ArrayList<Student> list) {
            mlist=list;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int i) {
            return mlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = LayoutInflater.from(context).inflate(R.layout.list_layout, viewGroup, false);
            TextView textViewName =v.findViewById(R.id.textviewName);

            Student user=mlist.get(i);
            textViewName.setText(user.getName());
            return v;
        }
    }

