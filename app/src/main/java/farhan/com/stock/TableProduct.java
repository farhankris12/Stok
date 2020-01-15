package farhan.com.stock;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import farhan.com.stock.Model.*;

import java.util.Comparator;

import de.codecrafters.tableview.SortableTableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import de.codecrafters.tableview.toolkit.SortStateViewProviders;
import de.codecrafters.tableview.toolkit.TableDataRowBackgroundProviders;
import farhan.com.stock.Model.Produk;
import farhan.com.stock.R;

public class TableProduct extends SortableTableView<Produk>
{
	public TableProduct(Context ctx){
		super(ctx, null);
	}
	public TableProduct(final Context context, final AttributeSet attributes) {
        this(context, attributes, android.R.attr.listViewStyle);
    }

    public TableProduct(final Context context, final AttributeSet attributes, final int styleAttributes) {
        super(context, attributes, styleAttributes);
		setColumnCount(5);
		SimpleTableHeaderAdapter simpleTableHeaderAdapter = new SimpleTableHeaderAdapter(context,  "Tgl", "Produk", "Harga","Stok", "Pemasok");
		simpleTableHeaderAdapter.setTextColor(ContextCompat.getColor(context, R.color.table_header_text));
		final int rowColorEven = ContextCompat.getColor(context, R.color.table_data_row_even);
        final int rowColorOdd = ContextCompat.getColor(context, R.color.table_data_row_odd);
        setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(rowColorEven, rowColorOdd));
        setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());
		setHeaderAdapter(simpleTableHeaderAdapter);
        setColumnComparator(0, new NamaProdukComparator());
		setColumnComparator(1, new NamaProdukComparator());
		setColumnComparator(2, new HargaProdukComparator());
		setColumnComparator(3, new StokProdukComparator());
        setColumnComparator(4, new NamaProdukComparator());
	}
	private static class HargaProdukComparator implements Comparator<Produk> {
        @Override
        public int compare(Produk prod1, Produk prod2) {
            if (prod1.getHarga() < prod2.getHarga()) return -1;
            if (prod1.getHarga() > prod2.getHarga()) return 1;
            return 0;
        }
	}
	private static class StokProdukComparator implements Comparator<Produk> {
        @Override
        public int compare(Produk prod1, Produk prod2) {
            if (prod1.getStok() < prod2.getStok()) return -1;
            if (prod1.getStok()> prod2.getStok()) return 1;
            return 0;
        }
	}
	private static class NamaProdukComparator implements Comparator<Produk> {

        @Override
        public int compare(final Produk prod1, final Produk prod2) {
            return prod1.getNama().compareTo(prod2.getNama());
        }
    }
}
