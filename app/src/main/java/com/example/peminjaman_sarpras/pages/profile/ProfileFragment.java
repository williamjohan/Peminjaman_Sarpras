package com.example.peminjaman_sarpras.pages.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.peminjaman_sarpras.MainActivity;
import com.example.peminjaman_sarpras.R;


public class ProfileFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setStatusBarColor(ContextCompat.getColor(getContext(), R.color.white));
        ((MainActivity) getActivity()).setStatusBarTextDark(true);
    }

}