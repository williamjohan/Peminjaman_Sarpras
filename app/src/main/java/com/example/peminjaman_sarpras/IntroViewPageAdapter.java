package com.example.peminjaman_sarpras;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class IntroViewPageAdapter extends PagerAdapter {

    Context mcontex;
    List<ScreenItem> mListScreen ;

    public IntroViewPageAdapter(Context mcontex, List<ScreenItem> mListScreen) {
        this.mcontex = mcontex;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mcontex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen, null);

        //membuat inflater layout membaca item item nya
        ImageView imgSlide = layoutScreen.findViewById(R.id.image_intro);
        TextView title = layoutScreen.findViewById(R.id.TVjudul);
        TextView description = layoutScreen.findViewById(R.id.TVDesc);

/** ngecek kalau masuk ke View Adapternya
//        Log.d("IntroViewPageAdapter", "Position: " + position);
//        Log.d("IntroViewPageAdapter", "Title: " + mListScreen.get(position).getTitle());
//        Log.d("IntroViewPageAdapter", "Description: " + mListScreen.get(position).getDescription());
//        Log.d("IntroViewPageAdapter", "Image Resource: " + mListScreen.get(position).getScreenImg());
 */

        title.setText(mListScreen.get(position).getTitle());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());
        description.setText(mListScreen.get(position).getDescription());



        //setelah sudah masukkan view layoutscreen nya kedalam container
        container.addView(layoutScreen);
        return layoutScreen;
        //return super.instantiateItem(container, position);


    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }
}
