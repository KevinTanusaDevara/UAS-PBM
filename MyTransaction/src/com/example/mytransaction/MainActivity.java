package com.example.mytransaction;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText us, ps;
	Button login, reset;
	TextView pesan;
	String res, id;
	public String sep = "|";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        us = (EditText) findViewById(R.id.regusername);
		ps = (EditText) findViewById(R.id.regpassword);
		login = (Button) findViewById(R.id.btnSubmit);
		reset = (Button) findViewById(R.id.btnReset);
		TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
		// Atur link teks "Belum punya akun?" ke register new account
        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Beralih ke tampilan screen Register
                tambah_user(v);
			}
		});
        login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
				postParameters.add(new BasicNameValuePair("username", us.getText().toString()));
				postParameters.add(new BasicNameValuePair("password", ps.getText().toString()));
				String response = null;
				try {
					response = JSONParser.executeHttpPost("https://kevintd.000webhostapp.com/Login.php", postParameters);
					res = response.toString();
					res = res.trim();
					res = res.replaceAll("\\s+", "");
					/* String valid = "1"; */
					if (cariString().equals("1")) {
						id = cariString();
						Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_LONG).show();
						berhasil(v);
					}
					else {
						Toast.makeText(getApplicationContext(), "Login Gagal!", Toast.LENGTH_LONG).show();
					}
				}
				catch (Exception e) {
					us.setText(e.toString());
				}
			}
		});
        reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				us.setText("");
				ps.setText("");
			}
		});
    }
    public void tambah_user(View v) {
		Intent daftar = new Intent(this, RegisterActivity.class);
		startActivity(daftar);
		finish();
	}
	public void berhasil(View theButton) {
		Intent i = new Intent(this, MainMenuActivity.class);
		i.putExtra("id", id); //Put session id berupa extras
		startActivity(i);
		finish();
	}
	public String cariString(){
		String temp = res.substring(0, res.indexOf(sep.charAt(0)));
		res = res.substring(res.indexOf(sep.charAt(0)) + 1);
		return temp;
	}
}