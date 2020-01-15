package farhan.com.stock.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import farhan.com.stock.DBHelper;
import farhan.com.stock.Model.Produk;
import farhan.com.stock.Model.*;

import java.text.NumberFormat;
import java.util.ArrayList;

import de.codecrafters.tableview.TableDataAdapter;

public class ProdukDataAdapter extends TableDataAdapter
{
	public static final NumberFormat PRICE_FORMATTER = NumberFormat.getNumberInstance();
	public ProdukDataAdapter(Context ctx, ArrayList<Produk> prod){
		super(ctx, prod);
	}
	@Override
	public View getCellView(int row, int column, ViewGroup p3) {
		Produk produk = (Produk) getRowData(row);
		View render=null;
		switch(column){
			case 0:
				render=renderString(produk.getdatecreate());
				break;
			case 1:
				render=renderString(produk.getNama());
				break;
			case 2:
				render=renderString("Rp. "+PRICE_FORMATTER.format(produk.getHarga()));
				break;
			case 3:
				render=renderString(""+produk.getStok());
				break;
			case 4:
				render=renderString(produk.getPemasok());
				break;
		}
		return render;
	}
	private int getpos(Produk p){
		int pos = -1;
		for(Object pp:getData()){
			Produk ppp=(Produk)pp;
			if(ppp.getSn().equals(p.getSn())){
				pos=getData().indexOf(pp);
				break;
			}
		}
		return pos;
	}
	public void tambah(ContentValues val){
		getData().add(new Produk(val.getAsString("nama"), val.getAsString("sn"), val.getAsLong("harga"), val.getAsInteger("stok"), val.getAsString("datecreate"), val.getAsString("createname"), val.getAsString("dateupdate"), val.getAsString("updatename"), val.getAsString("pemasok"), val.getAsString("keterangan")));
		new DBHelper(getContext()).tambah(val);
		notifyDataSetChanged();
	}
	public void hapus(Produk produk){
		getData().remove(produk);
		new DBHelper(getContext()).delete(produk.getSn());
		notifyDataSetChanged();
	}
	public void perbarui(Produk produk, ContentValues newdata){
		int idx=getpos(produk);
		if(idx>=0){
			
		}else{
			return;
		}
		getData().set(idx, new Produk(newdata.getAsString("nama"), newdata.getAsString("sn"), newdata.getAsLong("harga"), newdata.getAsInteger("stok"), newdata.getAsString("datecreate"), newdata.getAsString("createname"), newdata.getAsString("dateupdate"), newdata.getAsString("updatename"), newdata.getAsString("pemasok"), newdata.getAsString("keterangan")));
		new DBHelper(getContext()).update(newdata, produk.getSn());
		notifyDataSetChanged();
	}
	private View renderString(final String value) {
        final TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 30, 20, 30);
        textView.setTextSize(14);
        return textView;
    }
	
}
