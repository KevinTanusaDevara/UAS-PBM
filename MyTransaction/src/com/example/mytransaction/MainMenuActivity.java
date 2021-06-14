package com.example.mytransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenuActivity extends Activity{
	String id;
	Button transaksi, histori, laporan;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        transaksi = (Button) findViewById(R.id.btnTransaksi);
		histori = (Button) findViewById(R.id.btnHistori);
		id = this.getIntent().getStringExtra("id"); //Ambil id user dan simpan di sebuah variable.
		transaksi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent haltransaksi = new Intent(getApplicationContext(), TransactionActivity.class);
				haltransaksi.putExtra("id", id);
				startActivity(haltransaksi);
				finish();
			}
		});
		histori.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent halhistori = new Intent(getApplicationContext(), HistoryActivity.class);
				halhistori.putExtra("id", id);
				startActivity(halhistori);
				finish();
			}
		});
	}
}