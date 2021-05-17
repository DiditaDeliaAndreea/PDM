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

public class povesteDeCraciun extends Fragment {

    public static com.example.bookfinder.ui.home.povesteDeCraciun newInstance() {
        return new com.example.bookfinder.ui.home.povesteDeCraciun();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poveste_de_craciun, container, false);
        ImageView simpleImageView=(ImageView) view.findViewById(R.id.window2_image2);

        return view;
    }


}