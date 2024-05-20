package com.example.peminjaman_sarpras.pages.riwayat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.peminjaman_sarpras.MainActivity;
import com.example.peminjaman_sarpras.R;
import com.google.android.material.tabs.TabLayout;


public class RiwayatFragment extends Fragment  {
    private TabLayout tabLayout;
    private NavHostFragment navHostFragment;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);



        if (getArguments() != null) {
            int idRuangan = getArguments().getInt("idRuangan", -1);
            Log.d("RiwayatFragment", "idRuangan: " + idRuangan);
            // Kirim id ruangan ke PesananProsesFragment menggunakan Bundle
            ProsesFragment prosesFragment = new ProsesFragment();
            Bundle args = new Bundle();
            args.putInt("idRuangan", idRuangan);
            prosesFragment.setArguments(args);

            // Ganti fragment default dengan PesananProsesFragment
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, prosesFragment)
                    .commit();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi TabLayout dan NavHostFragment
        tabLayout = view.findViewById(R.id.tablayoutRiwayat);
        navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerView);

        // Atur listener untuk mengubah fragment saat tab dipilih
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        navHostFragment.getNavController().navigate(R.id.navigation_proses);
                        break;
                    case 1:
                        navHostFragment.getNavController().navigate(R.id.navigation_batal);
                        break;
                    case 2:
                        navHostFragment.getNavController().navigate(R.id.navigation_selesai);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setStatusBarColor(ContextCompat.getColor(getContext(), R.color.getstarted));
        ((MainActivity) getActivity()).setStatusBarTextDark(false);
    }
}