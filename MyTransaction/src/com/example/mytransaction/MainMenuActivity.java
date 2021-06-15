package com.example.mytransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainMenuActivity extends Activity{
	String id;
	ImageView imgTransaksi, imgHistori;
	TextView txtTransaksi, txtHistori;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imgTransaksi = (ImageView) findViewById(R.id.imgTransaksi);
        imgHistori = (ImageView) findViewById(R.id.imgHistori);
        txtTransaksi = (TextView) findViewById(R.id.txtTransaksi);
        txtHistori = (TextView) findViewById(R.id.txtHistori);
		id = this.getIntent().getStringExtra("id"); //Ambil id user dan simpan di sebuah variable.
		imgTransaksi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent haltransaksi = new Intent(getApplicationContext(), TransactionActivity.class);
				haltransaksi.putExtra("id", id);
				startActivity(haltransaksi);
				finish();
			}
		});
		imgHistori.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent halhistori = new Intent(getApplicationContext(), HistoryActivity.class);
				halhistori.putExtra("id", id);
				startActivity(halhistori);
				finish();
			}
		});
		txtTransaksi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent haltransaksi = new Intent(getApplicationContext(), TransactionActivity.class);
				haltransaksi.putExtra("id", id);
				startActivity(haltransaksi);
				finish();
			}
		});
		txtHistori.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent halhistori = new Intent(getApplicationContext(), HistoryActivity.class);
				halhistori.putExtra("id", id);
				startActivity(halhistori);
				finish();
			}
		});
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    //Handle the back button
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	        switch (event.getAction()) {
		        case KeyEvent.ACTION_DOWN:
		        	Intent login = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(login);
					finish();
		            return true;
		        default:
		        	return false;
	        }
	    }
		else{
			return false;
		}
	}
}