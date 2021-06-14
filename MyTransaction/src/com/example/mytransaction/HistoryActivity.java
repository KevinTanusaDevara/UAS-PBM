package com.example.mytransaction;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class HistoryActivity extends Activity{
	private List<Transaction> transactionList = new ArrayList<Transaction>();
	private ListTransaction adapter;
	ListView listView;
	public String res, id;
	public String sep = "|";
	@Override
	public void startActivity(Intent intent) {      
	    // Check if search intent
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	        intent.putExtra("id", id);
	    }
	    super.startActivity(intent);
	}
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaksi);
        listView = (ListView)findViewById(R.id.listtransaksi);
		adapter = new ListTransaction(HistoryActivity.this, transactionList);
		listView.setAdapter(adapter);
		id = this.getIntent().getStringExtra("id"); //Ambil id user dan simpan di sebuah variable.
		Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
        	String query = intent.getStringExtra(SearchManager.QUERY);
        	findTransaction(query);
        }
        else{
        	reloadData();
        }
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
	public String cariString(){
		String temp = res.substring(0, res.indexOf(sep.charAt(0)));
		res = res.substring(res.indexOf(sep.charAt(0)) + 1);
		return temp;
	}
	private void reloadData(){
		transactionList.clear();
    	ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    	postParameters.add(new BasicNameValuePair("id", id)); //Kirim id user.
    	try{
        	String response = JSONParser.executeHttpPost("https://kevintd.000webhostapp.com/GetAllTransaction.php", postParameters);
			res = response.toString();
			if(res.length() > 2){
				int rowcount = Integer.valueOf(cariString());
				for(int i = 1; i <= rowcount; i++){
					Transaction transaksi = new Transaction();
					transaksi.setNum(i);
					transaksi.setNominal(Double.valueOf(cariString()));
					transaksi.setTanggal(cariString());
					transaksi.setJenis(cariString());
					transaksi.setKeterangan(cariString());
	            	transactionList.add(transaksi);
				}
			}
			else{
				Toast.makeText(getApplicationContext(), "Histori transaksi kosong.", Toast.LENGTH_LONG).show();
			}
    	}
    	catch(Exception e){
    		//Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.simpleSearchView);
        searchView.setSubmitButtonEnabled(true);
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
		return true;
	}
	public void findTransaction(String query){
		transactionList.clear();
    	ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    	postParameters.add(new BasicNameValuePair("id", id));
    	postParameters.add(new BasicNameValuePair("query", query));
    	try{
        	String response = JSONParser.executeHttpPost("https://kevintd.000webhostapp.com/GetSpecificTransaction.php", postParameters);
			res = response.toString();
			if(res.length() > 2){
				int rowcount = Integer.valueOf(cariString());
				for(int i = 1; i <= rowcount; i++){
					Transaction transaksi = new Transaction();
					transaksi.setNum(i);
					transaksi.setNominal(Double.valueOf(cariString()));
					transaksi.setTanggal(cariString());
					transaksi.setJenis(cariString());
					transaksi.setKeterangan(cariString());
	            	transactionList.add(transaksi);
				}
			}
			else{
				Toast.makeText(getApplicationContext(), "Tidak ada transaksi dengan keterangan atau nominal tersebut.", Toast.LENGTH_LONG).show();
			}
    	}
    	catch(Exception e){
    		 //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    	}
	}
}