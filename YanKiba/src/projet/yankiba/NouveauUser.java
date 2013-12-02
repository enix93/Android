package projet.yankiba;

import java.sql.Connection;
import myconnections.DBConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import myconnections.DBConnection;
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

public class NouveauUser extends Activity {
	protected  Button mButton1 = null;
	protected  Button mButton2 = null;
	protected TextView number=null;
	protected TextView pref=null;
	protected int telephone;
	protected int prefixeReduit;
	protected Connection dbc=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nouveau_user);
		TelephonyManager mTelephonyMgr;  
		mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);   
		String yourNumber = mTelephonyMgr.getLine1Number();
		
		telephone= Integer.parseInt(yourNumber.substring(3));
		prefixeReduit=Integer.parseInt(yourNumber.substring(1,3));
		Log.d("lol",Integer.toString(prefixeReduit));
		Log.d("lol",Integer.toString(telephone));
		Log.d("lol","lol");
		MyAccesDB adb = new MyAccesDB(NouveauUser.this);
		adb.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nouveau_user, menu);
		return true;
	}

	

	class MyAccesDB extends AsyncTask<String, Integer, Boolean> {
		public MyAccesDB(NouveauUser pActivity) {
			link(pActivity);
		}
		private void link(NouveauUser pActivity) {
		}
	
		@Override
		protected Boolean doInBackground(String... arg0) {
			Connection con = new DBConnection().getConnection();
			if (con == null) {
				return false;
			}
			UtilisateurDB.setConnection(con);
			// Statement stmt = dbConnect.createStatement();
			try {
				UtilisateurDB user = new UtilisateurDB(prefixeReduit,telephone);
				user.read();
			} catch (Exception e) {
			}
			return true;
		}
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			// Toast.makeText(RechercheActivity.this, resultat, Toast.LENGTH_LONG).show();
		
		}
	}

}
