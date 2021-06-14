package com.example.mytransaction;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity{
	EditText un, pw, name;
	Button simpan, reset;
	TextView pesan;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		un = (EditText) findViewById(R.id.regusername);
		pw = (EditText) findViewById(R.id.regpassword);
		name = (EditText) findViewById(R.id.regname);
		simpan = (Button) findViewById(R.id.btnSubmit);
		reset = (Button) findViewById(R.id.btnReset);
		TextView logInScreen = (TextView) findViewById(R.id.link_to_login);
		logInScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Beralih ke tampilan screen Register
				Intent i = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
				finish();
			}
		});
		simpan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(un.getText().toString().equals("") || pw.getText().toString().equals("") || name.getText().toString().equals("")){
					Toast.makeText(getApplication(), "Harap isi semua data terlebih dahulu!", Toast.LENGTH_LONG).show();
				}
				else{
					ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("username", un.getText().toString()));
					postParameters.add(new BasicNameValuePair("password", pw.getText().toString()));
					postParameters.add(new BasicNameValuePair("name", name.getText().toString()));
					/* String valid = "1"; */
					String response = null;
					try {
						response = JSONParser.executeHttpPost("https://kevintd.000webhostapp.com/Register.php", postParameters);
						String res = response.toString();
						res = res.trim();
						res = res.replaceAll("\\s+", "");
						if (res.equals("1")){
							Toast.makeText(getApplication(), "Data Tersimpan!", Toast.LENGTH_LONG).show();
							Intent i = new Intent(getApplicationContext(), MainActivity.class);
							startActivity(i);
							finish();
						}
						else{
							Toast.makeText(getApplication(), "Data gagal disimpan!", Toast.LENGTH_LONG).show();
						}
					}
					catch (Exception e) {
						un.setText(e.toString());
					}
				}
			}
		});
		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				un.setText("");
				pw.setText("");
				name.setText("");
			}
		});
	}
}
