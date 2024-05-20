package com.example.peminjaman_sarpras.pages.beranda;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peminjaman_sarpras.R;
import com.example.peminjaman_sarpras.adapter.RuanganAdapter;
import com.example.peminjaman_sarpras.model.Ruangan_Model;
import com.example.peminjaman_sarpras.database.DBHelper;

import java.util.List;

public class RuanganPages extends AppCompatActivity {

    private TextView judulbar;
    private ImageView imgback;

    private RecyclerView recyclerView;
    private RuanganAdapter subcontentadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ruangan);

        //menghilangkan action bar
        getSupportActionBar().hide();

        //init var
        judulbar = findViewById(R.id.judulbar);
        imgback = findViewById(R.id.backicon);
        recyclerView = findViewById(R.id.RVsubcontent);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();

        judulbar.setText(extras.getString("namacontent"));

        int idcontent = extras.getInt("idlistcontent");

        // ngecek aja di log
//        Log.d("RuanganPages", "namacontent: " + tampungnamacontent);
//        Log.d("RuanganPages", "idlistcontent: " + idcontent);


        //inisiasi class dbhelper
        DBHelper db = new DBHelper(this);
        //memanggil isi data di dbhelper ditampung di listcontent
        List<Ruangan_Model> listsubcontent = db.getallruangan(idcontent);

        //memasukkan isi listcontent ke adapter
        subcontentadapter = new RuanganAdapter(listsubcontent,this);
        recyclerView.setAdapter(subcontentadapter);


        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}