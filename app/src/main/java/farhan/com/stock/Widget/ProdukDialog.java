package farhan.com.stock.Widget;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import farhan.com.stock.MainActivity;
import farhan.com.stock.Model.Produk;
import farhan.com.stock.*;
import farhan.com.stock.Model.*;

public class ProdukDialog
{
	public ProdukDialog(final Context ctx, final Produk dataset){
		String positiveTxt="Tambahkan";
		AlertDialog.Builder dlg=new AlertDialog.Builder(ctx);
		View form=LayoutInflater.from(ctx).inflate(R.layout.produkdlg, null);
		final TextView nama=(TextView) form.findViewById(R.id.namaproduk);
		final TextView kodeprod=(TextView) form.findViewById(R.id.kodeproduk);
		final TextView harga=(TextView) form.findViewById(R.id.harga);
		final TextView stok=(TextView) form.findViewById(R.id.stok);
		final TextView pemasok=(TextView) form.findViewById(R.id.nmpemasok);
		final TextView keterangan=(TextView) form.findViewById(R.id.keterangan);

		if(dataset!=null){
			nama.setText(dataset.getNama());
			kodeprod.setText(dataset.getSn());
			pemasok.setText(dataset.getPemasok());
			keterangan.setText(dataset.getKeterangan());
			harga.setText(""+dataset.getHarga());
			stok.setText(""+dataset.getStok());
			positiveTxt="Perbarui";
			dlg.setNeutralButton("Hapus", null);
		}
		dlg.setView(form);
		dlg.setTitle(positiveTxt+" Produk");
		dlg.setPositiveButton(positiveTxt, new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2) {
					ContentValues data = new ContentValues();
					data.put("nama", nama.getText().toString());
					data.put("sn", kodeprod.getText().toString());
					data.put("pemasok", pemasok.getText().toString());
					data.put("keterangan", keterangan.getText().toString());
					data.put("harga", Long.parseLong(harga.getText().toString()));
					data.put("stok", Integer.parseInt(stok.getText().toString()));
					data.put("datecreate",  Now().substring(0, 10));
					data.put("createname", "adm");
					data.put("dateupdate", "");
					data.put("updatename", "");
					if(dataset==null){
						MainActivity.dataproduk.tambah(data);
					}else{
						MainActivity.dataproduk.perbarui(dataset, data);
					}
				}
			});
		dlg.setNegativeButton("Batal", null);
		final AlertDialog dialog=dlg.create();
		dialog.show();

		Button hapusBtn=dialog.getButton(AlertDialog.BUTTON3);
		hapusBtn.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View p1) {
					Toast.makeText(p1.getContext(), "Tekan lama untuk menghapus", Toast.LENGTH_LONG).show();
				}
			});
		hapusBtn.setOnLongClickListener(new View.OnLongClickListener(){
				@Override
				public boolean onLongClick(View p1) {
					MainActivity.dataproduk.hapus(dataset);
					dialog.dismiss();
					Toast.makeText(p1.getContext(), "Terhapus", Toast.LENGTH_SHORT).show();
					return false;
				}
			});

		final Button btn= dialog.getButton(AlertDialog.BUTTON1);
		if(dataset==null) btn.setEnabled(false);
		TextWatcher watcher=new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
			}
			@Override
			public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
				if(nama.getText().length()>3&&kodeprod.getText().length()>5&&harga.getText().length()>2) btn.setEnabled(true);
				else btn.setEnabled(false);
			}
			@Override
			public void afterTextChanged(Editable p1) {
			}
		};
		nama.addTextChangedListener(watcher);
		kodeprod.addTextChangedListener(watcher);
		harga.addTextChangedListener(watcher);
	}
	public static String Now() {
		Calendar calendar = Calendar.getInstance();

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}
}
