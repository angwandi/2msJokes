package com.example.liband2msjokes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class libandFragment extends Fragment {
    public libandFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.liband_fragment, container, false);
        TextView newText = root.findViewById(R.id.liband_tv);
        String newString;
        /*Get Intent from JokesActivity button click*/
        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        newString = intent.getStringExtra(getString(R.string.envelopeJokes));
        if (newString != null) {
            newText.setText(newString);
        } else {
            newText.setText(R.string.nojokes);
        }
        return root;
    }
}
