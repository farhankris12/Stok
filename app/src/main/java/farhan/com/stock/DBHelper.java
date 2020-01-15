package farhan.com.stock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase dbw;
    public static String namaTable = "produk";
    public static String userTable = "user";

    public DBHelper(Context ctx) {
        super(ctx, namaTable + ".db", null, 2);
        dbw = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + namaTable + " (sn TEXT null,nama TEXT null,harga INTEGER null, stok INTEGER null, datecreate text null, createname text null, dateupdate text null, updatename text null, pemasok text null, keterangan text null)");
        db.execSQL("create table " + userTable + " (nama TEXT null,password TEXT null)");
        Cursor cur = db.rawQuery("SELECT * FROM " + userTable, null);
        cur.moveToFirst();
        if (cur.getCount() == 0) {
            Integer x = 5;
            for (int i = 0; i < x; i++) {
                ContentValues cv = new ContentValues();
                cv.put("nama", "Stadm" + i);
                cv.put("password", "123456" + i);
                long rowID = db.insertOrThrow("user", null, cv);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int p2, int p3) {
        db.execSQL("DROP TABLE IF EXISTS " + namaTable);
    }

    public void tambah(ContentValues val) {
        dbw.insert(namaTable, null, val);
    }

    public void update(ContentValues val, String sn) {
        dbw.update(namaTable, val, "sn=" + "\"" + sn + "\"", null);
    }

    public void delete(String sn) {
        dbw.delete(namaTable, "sn=" + "\"" + sn + "\"", null);
    }

    public Cursor semuaData() {
        Cursor cur = dbw.rawQuery("SELECT * FROM " + namaTable, null);
        return cur;
    }

    public Cursor baca(String sn) {
        Cursor cur = dbw.rawQuery("select * from produk where sn=" + "\"" + sn + "\"", null);
        return cur;
    }

    public Cursor getuser(String password, String nama) {
        Cursor cur = dbw.rawQuery("select * from user where password=" + "\"" + password + "\" and nama=" + "\"" + nama + "\"", null);
        return cur;
    }
}
