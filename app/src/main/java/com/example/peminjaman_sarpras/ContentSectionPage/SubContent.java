package com.example.peminjaman_sarpras.ContentSectionPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.MainActivity;
import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.SubContentAdapter;
import com.example.peminjaman_sarpras.constructor.SubContentConstructor;
import com.example.peminjaman_sarpras.database.DBHelper;

import java.util.List;

public class SubContent extends AppCompatActivity {

    private TextView judulbar;
    private ImageView imgback;

    private RecyclerView recyclerView;
    private SubContentAdapter subcontentadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_content);

        //menghilangkan action bar
        getSupportActionBar().hide();

        //init var
        judulbar = findViewById(R.id.judulbar);
        imgback = findViewById(R.id.backicon);

        recyclerView = findViewById(R.id.RVsubcontent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //memasukkan data yang diterima dari intent ContentAdapter
        Bundle extras = getIntent().getExtras();

        judulbar.setText(extras.getString("namacontent"));
        int idcontent = extras.getInt("idlistcontent");


        //inisiasi class dbhelper
        DBHelper db = new DBHelper(this);
        //memanggil isi data di dbhelper ditampung di listcontent
        List<SubContentConstructor> listsubcontent = db.getallruangan(idcontent);


        //memasukkan isi listcontent ke adapter
        subcontentadapter = new SubContentAdapter(listsubcontent,this);
        recyclerView.setAdapter(subcontentadapter);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}