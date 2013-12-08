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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private  Button OK ;
	private  Button cancel;
	private Button alert;
	private TextView number=null;
	private TextView pref=null;
	private int telephone;
	private int prefixeReduit;
	private Connection dbc=null;;
	private UtilisateurDB test =null;
    private TelephonyManager mTelephonyMgr;       
	public static final String Uuser="Utilisateur";
	private EditText rentrer=null;
	private String recuperationtel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String prefixe;
		String numero;
		String user;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String yourNumber =mTelephonyMgr.getLine1Number();
		telephone= Integer.parseInt(yourNumber.substring(3));
		prefixeReduit=Integer.parseInt(yourNumber.substring(1,3));
		MyAccesDB adb = new MyAccesDB(MainActivity.this);
		adb.execute();
		DBConnection dbc=new DBConnection();
	    Connection connect=dbc.getConnection();
	    UtilisateurDB.setConnection(connect);
	    cancel=(Button)findViewById(R.id.cancel);
	    rentrer =(EditText) findViewById(R.id.rentrer);
		OK=(Button)findViewById(R.id.follow);
	    OK.setOnClickListener(new OnClickListener() {
  			public void onClick(View v) {
  				CreationDB efg = new CreationDB(MainActivity.this);
  				efg.execute();
  			}
  			});
	    
	    cancel.setOnClickListener(new OnClickListener() {
  			public void onClick(View v) {
  				rentrer.setText("Entrez un nom");
  			}
  			});
		
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
				Log.d("lol","lol"+test.getPseudo());
				
			} catch (Exception e) {
			}
			 if(test.getPseudo()!=""&&test.getPseudo()!=null){
		    	 Intent i = new Intent(MainActivity.this, MainPage.class);
					i.putExtra(Uuser,test);
					startActivity(i);
		     }
		     else{
		    	
		     }
			return true;
		}
	}
	
		class CreationDB extends AsyncTask<String, Integer, Boolean> {
			public CreationDB(MainActivity mainActivity) {
				link(mainActivity);

			}
			private void link(MainActivity mainActivity) {
			}
		
			@Override
			protected Boolean doInBackground(String... arg0) {
				String prefixe;
				String numero;
				mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				String yourNumber = mTelephonyMgr.getLine1Number(); 
				telephone= Integer.parseInt(yourNumber.substring(3));
				prefixeReduit=Integer.parseInt(yourNumber.substring(1,3));
				Connection con = new DBConnection().getConnection();
				Log.d("on est ici","lol");
				recuperationtel=rentrer.getText().toString();
				test=new UtilisateurDB(telephone,prefixeReduit,recuperationtel);
				if (con == null) {
					
					return false;
				}
			
				UtilisateurDB.setConnection(con);
				try {
					Log.d("on est ici","lol");
					test.create();
					
					
				} catch (Exception e) {
				}
				
				return true;
			}
		
		
		
		
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
		     if(test.getPseudo()!=""){
		    	 Intent i = new Intent(MainActivity.this, MainPage.class);
					i.putExtra(Uuser,test);
					
					startActivity(i);
					finish();
		     }
		     else{
		   
		     }
		     
		}
	}
	
}