package com.example.mytransaction;

import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListTransaction extends BaseAdapter{
	private Activity context;
	private LayoutInflater inflater;
	private List<Transaction> NasItem;
	public ListTransaction(Activity activity, List<Transaction> NasItem) {
		ListTransaction.this.context = activity;
		ListTransaction.this.NasItem = NasItem;
	}

	@Override
	public int getCount() {
		return NasItem.size();
	}

	@Override
	public Object getItem(int location) {
		return NasItem.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (inflater == null){
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		if (view == null){
			view = inflater.inflate(R.layout.list_transaksi, null);
		}
		View rowView= inflater.inflate(R.layout.list_transaksi, null, true);
		TextView tvNum = (TextView) rowView.findViewById(R.id.tvNum);
		TextView tvNominal = (TextView) rowView.findViewById(R.id.tvNominal);
		TextView tvTanggal = (TextView) rowView.findViewById(R.id.tvTanggal);
		TextView tvJenis = (TextView) rowView.findViewById(R.id.tvJenis);
		TextView tvKeterangan = (TextView) rowView.findViewById(R.id.tvKeterangan);
		Transaction transaksi = NasItem.get(position);
		tvNum.setText(String.valueOf(transaksi.getNum()) + ".");
		tvNominal.setText("Nominal : Rp. " + String.valueOf(transaksi.getNominal()));
		tvTanggal.setText("Tanggal transaksi : " + transaksi.getTanggal());
		tvJenis.setText("Jenis transaksi : " + transaksi.getJenis());
		tvKeterangan.setText("Keterangan : " + transaksi.getKeterangan());
		return rowView;
	}
}
