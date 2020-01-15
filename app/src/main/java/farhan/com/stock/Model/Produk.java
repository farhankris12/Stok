package farhan.com.stock.Model;

import android.content.Context;
import android.database.Cursor;

import farhan.com.stock.DBHelper;

import java.util.ArrayList;

public class Produk
{
	protected String nama;
	protected String sn;
	protected long harga;
	protected int stok;
	protected String pemasok;
	protected String keterangan;
	protected String datecreate;
	protected String createname;
	protected String dateupdate;
	protected String updatename;

	public Produk(String nama, String sn, long harga, int stok, String datecreate, String createname, String dateupdate, String updatename, String pemasok, String keterangan ) {
		this.nama = nama;
		this.sn = sn;
		this.harga = harga;
		this.stok=stok;
		this.pemasok = pemasok;
		this.keterangan = keterangan;
		this.datecreate = datecreate;
		this.createname = createname;
		this.dateupdate = dateupdate;
		this.updatename = updatename;
	}

	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getStok() {
		return stok;
	}
	public void setStok(int stok) {
		this.stok = stok;
	}

	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setHarga(long harga) {
		this.harga = harga;
	}
	public long getHarga() {
		return harga;
	}

	public String getPemasok() {
		return pemasok;
	}
	public void setPemasok(String pemasok) {
		this.pemasok = pemasok;
	}

	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getdatecreate() {
		return datecreate;
	}
	public void setdatecreate(String datecreate) {
		this.datecreate = datecreate;
	}

	public String getcreatename() {
		return createname;
	}
	public void setcreatename(String createname) {
		this.createname = createname;
	}

	public String getdateupdate() {
		return dateupdate;
	}
	public void setdateupdate(String dateupdate) {
		this.dateupdate = dateupdate;
	}

	public String getupdatename() {
		return updatename;
	}
	public void setupdatename(String updatename) {
		this.updatename = updatename;
	}

	public static Produk getBySN(Context ctx, String SN){
		Cursor cur=new DBHelper(ctx).baca(SN);
		if(!cur.moveToFirst()) return null;
		if(cur.getCount()<1){
			return null;
		}
		String nama=cur.getString(cur.getColumnIndex("nama"));
		String sn=cur.getString(cur.getColumnIndex("sn"));
		long harga=cur.getLong(cur.getColumnIndex("harga"));
		int stok=cur.getInt(cur.getColumnIndex("stok"));
		String pemasok=cur.getString(cur.getColumnIndex("pemasok"));
		String keterangan=cur.getString(cur.getColumnIndex("keterangan"));
		String datecreate=cur.getString(cur.getColumnIndex("datecreate"));
		String createname=cur.getString(cur.getColumnIndex("createname"));
		String dateupdate=cur.getString(cur.getColumnIndex("dateupdate"));
		String updatename=cur.getString(cur.getColumnIndex("updatename"));
		return new Produk(nama, sn, harga, stok, datecreate, createname, dateupdate, updatename, pemasok, keterangan );
	}

	public static ArrayList<Produk> getInit(Context ctx){
		ArrayList<Produk> prod=new ArrayList<Produk>();
		Cursor cur=new DBHelper(ctx).semuaData();
		cur.moveToFirst();
		for(int i=0;i<cur.getCount();i++){
			cur.moveToPosition(i);
			String nama=cur.getString(cur.getColumnIndex("nama"));
			String sn=cur.getString(cur.getColumnIndex("sn"));
			long harga=cur.getLong(cur.getColumnIndex("harga"));
			int stok=cur.getInt(cur.getColumnIndex("stok"));
			String pemasok=cur.getString(cur.getColumnIndex("pemasok"));
			String keterangan=cur.getString(cur.getColumnIndex("keterangan"));
			String datecreate=cur.getString(cur.getColumnIndex("datecreate"));
			String createname=cur.getString(cur.getColumnIndex("createname"));
			String dateupdate=cur.getString(cur.getColumnIndex("dateupdate"));
			String updatename=cur.getString(cur.getColumnIndex("updatename"));
			prod.add(new Produk(nama, sn, harga, stok, datecreate, createname, dateupdate, updatename, pemasok, keterangan ));
		}
		return prod;
	}
}
