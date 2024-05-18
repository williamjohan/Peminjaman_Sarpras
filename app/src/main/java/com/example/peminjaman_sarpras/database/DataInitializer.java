package com.example.peminjaman_sarpras.database;

public class DataInitializer {

    private DBHelper db;
    public DataInitializer(DBHelper db){
        this.db = db;
    }

    public void initDB(){
        db.dml("INSERT INTO list_peminjaman (nama_list,resgambar)" +
                "VALUES('Ruang Rapat','contentruangrapat')"
        );

        db.dml("INSERT INTO list_peminjaman (nama_list,resgambar)" +
                "VALUES('Co-Working Space','contentcoworkingspace')"
        );
        db.dml("INSERT INTO list_peminjaman (nama_list,resgambar)" +
                "VALUES('Aula Hall Room','contentaulahallroom')"
        );

        //Masukan data ruangan rapat ke DBHelper
        db.dml("INSERT INTO ruangan (id_list,nama_ruangan,deskripsi_ruangan,harga_ruangan,lokasi_ruangan,resgambar)" +
                "VALUES(1,'Prontera','cobacoba','125000','Lantai1','ruangrapat1')"
        );

        db.dml("INSERT INTO ruangan (id_list,nama_ruangan,deskripsi_ruangan,harga_ruangan,lokasi_ruangan,resgambar)" +
                "VALUES(1,'Aldebaran','cobacoba','200000','Lantai1','ruangrapat2')"
        );

        db.dml("INSERT INTO ruangan (id_list,nama_ruangan,deskripsi_ruangan,harga_ruangan,lokasi_ruangan,resgambar)" +
                "VALUES(2,'Geffen','cobacoba','100000','Lantai2','ruangrapat3')"
        );

        db.dml("INSERT INTO ruangan (id_list,nama_ruangan,deskripsi_ruangan,harga_ruangan,lokasi_ruangan,resgambar)" +
                "VALUES(2,'Alberta','cobacoba','150000','Lantai3','ruangrapat4')"
        );

        //masukan data gambar ke tabel gambar di dbhelper
        db.dml("INSERT INTO gambar (id_ruangan,resgambar)" +
                "VALUES(1,'ruangrapat1')"
        );
        db.dml("INSERT INTO gambar (id_ruangan,resgambar)" +
                "VALUES(1,'ruangrapat2')"
        );
        db.dml("INSERT INTO gambar (id_ruangan,resgambar)" +
                "VALUES(2,'ruangrapat3')"
        );
        db.dml("INSERT INTO gambar (id_ruangan,resgambar)" +
                "VALUES(2,'ruangrapat4')"
        );

    }
}


