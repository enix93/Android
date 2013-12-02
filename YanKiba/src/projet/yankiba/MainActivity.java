package projet.yankiba;



import java.sql.Connection;

import projet.yankiba.NouveauUser.MyAccesDB;
import myconnections.DBConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private  Button mButton1 = null;
	private  Button mButton2 = null;
	private TextView number=null;
	private TextView pref=null;
	private int telephone;
	private int prefixeReduit;
	private Connection dbc=null;;
	private UtilisateurDB test =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String prefixe;
		String numero;
		String user;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TelephonyManager mTelephonyMgr;  
		mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);   
		String yourNumber = mTelephonyMgr.getLine1Number();  
		telephone= Integer.parseInt(yourNumber.substring(3));
		prefixeReduit=Integer.parseInt(yourNumber.substring(1,3));
		MyAccesDB adb = new MyAccesDB(MainActivity.this);
		adb.execute();
		DBConnection dbc=new DBConnection();
	    Connection connect=dbc.getConnection();
	    UtilisateurDB.setConnection(connect);

		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	
}
	class MyAccesDB extends AsyncTask<String, Integer, Boolean> {
		public MyAccesDB(MainActivity mainActivity) {
			link(mainActivity);

		}
		private void link(MainActivity mainActivity) {
		}
	
		@Override
		protected Boolean doInBackground(String... arg0) {
			String prefixe;
			String numero;
			setContentView(R.layout.activity_main);
			TelephonyManager mTelephonyMgr;  
			mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);   
			String yourNumber = mTelephonyMgr.getLine1Number();  
			telephone= Integer.parseInt(yourNumber.substring(3));
			prefixeReduit=Integer.parseInt(yourNumber.substring(1,3));
			Connection con = new DBConnection().getConnection();
			test=new UtilisateurDB(telephone,prefixeReduit);
			Log.d("lol",Integer.toString(test.getPrefixe())+"lol");
			if (con == null) {
				
				return false;
			}
		
			UtilisateurDB.setConnection(con);
			// Statement stmt = dbConnect.createStatement();
			try {
				Log.d("lol","ici");
				test.read();
				Log.d("lol","ici");
				
			} catch (Exception e) {
			}
			return true;
		}
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			Toast.makeText(MainActivity.this,test.getPseudo() , Toast.LENGTH_LONG).show();
		    
		}
	}
}