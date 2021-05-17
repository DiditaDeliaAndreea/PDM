package com.example.bookfinder.ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bookfinder.R;

public class jurnalulAnneiFrank extends Fragment {

    public static jurnalulAnneiFrank newInstance() {
        return new jurnalulAnneiFrank();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_view_fragment, container, false);
        ImageView simpleImageView=(ImageView) view.findViewById(R.id.window2_image1);

        return view;
    }


}