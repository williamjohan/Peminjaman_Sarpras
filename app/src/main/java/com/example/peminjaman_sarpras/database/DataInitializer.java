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
                "VALUES(1,'Prontera','cobacoba','Rp.125000','Lantai1','ruangrapat1')"
        );

        db.dml("INSERT INTO ruangan (id_list,nama_ruangan,deskripsi_ruangan,harga_ruangan,lokasi_ruangan,resgambar)" +
                "VALUES(1,'Aldebaran','cobacoba','RP.200000','Lantai1','ruangrapat2')"
        );

        db.dml("INSERT INTO ruangan (id_list,nama_ruangan,deskripsi_ruangan,harga_ruangan,lokasi_ruangan,resgambar)" +
                "VALUES(2,'Geffen','cobacoba','RP.100000','Lantai2','ruangrapat3')"
        );

        db.dml("INSERT INTO ruangan (id_list,nama_ruangan,deskripsi_ruangan,harga_ruangan,lokasi_ruangan,resgambar)" +
                "VALUES(2,'Alberta','cobacoba','RP.150000','Lantai3','ruangrapat4')"
        );

    }
}


