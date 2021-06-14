package com.example.mytransaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TransactionActivity extends Activity{
	EditText regnominal, regtanggal, regketerangan;
	RadioGroup jenistransaksi;
	RadioButton pilihan;
	int selectedId;
	String id;
	Button submit, reset;
	Calendar myCalendar;
	DatePickerDialog.OnDateSetListener date;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_transaksi);
        regnominal = (EditText) findViewById(R.id.regnominal);
        regtanggal = (EditText) findViewById(R.id.regtanggal);
        regketerangan = (EditText) findViewById(R.id.regketerangan);
        jenistransaksi = (RadioGroup) findViewById(R.id.jenistransaksi);
        submit = (Button) findViewById(R.id.btnSubmit);
		reset = (Button) findViewById(R.id.btnReset);
		id = this.getIntent().getStringExtra("id"); //Ambil id user dan simpan di sebuah variable.
		myCalendar = Calendar.getInstance();
		date = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		        myCalendar.set(Calendar.YEAR, year);
		        myCalendar.set(Calendar.MONTH, monthOfYear);
		        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		        updateTanggal();
		    }
		};
		regtanggal.setFocusable(false);
		regtanggal.setOnClickListener(new OnClickListener() {
		     @SuppressLint("InlinedApi") @Override
		     public void onClick(View v) {
		         new DatePickerDialog(TransactionActivity.this, AlertDialog.THEME_HOLO_DARK, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
		     }
		 });
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(regnominal.getText().toString().equals("") || regtanggal.getText().toString().equals("") || regketerangan.getText().toString().equals("") || jenistransaksi.getCheckedRadioButtonId() == -1){
					Toast.makeText(getApplication(), "Harap isi semua data terlebih dahulu!", Toast.LENGTH_LONG).show();
				}
				else{
					ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("user_id", id));
					postParameters.add(new BasicNameValuePair("nominal", regnominal.getText().toString()));
					postParameters.add(new BasicNameValuePair("tanggal", regtanggal.getText().toString()));
					postParameters.add(new BasicNameValuePair("keterangan", regketerangan.getText().toString()));
					selectedId = jenistransaksi.getCheckedRadioButtonId();
		            // Find the RadioButton using returned id.
					pilihan = (RadioButton) findViewById(selectedId);
					postParameters.add(new BasicNameValuePair("jenistransaksi", pilihan.getText().toString()));
					/* String valid = "1"; */
					String response = null;
					try {
						response = JSONParser.executeHttpPost("https://kevintd.000webhostapp.com/AddTransaction.php", postParameters);
						String res = response.toString();
						res = res.trim();
						res = res.replaceAll("\\s+", "");
						if (res.equals("1")){
							Toast.makeText(getApplication(), "Data Tersimpan!", Toast.LENGTH_LONG).show();
							Intent mainmenu = new Intent(getApplicationContext(), MainMenuActivity.class);
							mainmenu.putExtra("id", id);
							startActivity(mainmenu);
							finish();
						}
						else{
							Toast.makeText(getApplication(), "Data gagal disimpan!", Toast.LENGTH_LONG).show();
						}
					}
					catch (Exception e) {
						regnominal.setText(e.toString());
					}
				}
			}
		});
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				regnominal.setText("");
				regtanggal.setText("");
				regketerangan.setText("");
				jenistransaksi.clearCheck();
			}
		});
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    //Handle the back button
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
	        switch (event.getAction()) {
		        case KeyEvent.ACTION_DOWN:
		        	Intent mainmenu = new Intent(getApplicationContext(), MainMenuActivity.class);
		        	mainmenu.putExtra("id", id);
					startActivity(mainmenu);
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
	private void updateTanggal() {
	    String myFormat = "yyyy-MM-dd"; //Date format
	    SimpleDateFormat sdf = new SimpleDateFormat(myFormat);   
	    regtanggal.setText(sdf.format(myCalendar.getTime()));
	 }
}