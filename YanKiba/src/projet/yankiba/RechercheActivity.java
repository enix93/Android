package projet.yankiba;
import java.sql.Connection;

import myconnections.DBConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class RechercheActivity extends Activity {

	EditText rechUser = null;
	EditText resUser = null;
	Button btnChat = null;
	Button btnRech = null;
	UtilisateurDB user = null;
	UtilisateurDB repeche=null;

	public static final String IDUSER = "utilisateurDB";
	public static final String Uuser="Utilisateur";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recherche);
		Intent intent = getIntent();
	     
	       if (intent != null) {
	    	  repeche=(UtilisateurDB)intent.getParcelableExtra(MainPage.Uuser);
	    	    
	       }
		rechUser = (EditText) findViewById(R.id.strRech);
		resUser = (EditText) findViewById(R.id.result);
		resUser.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				Intent i = new Intent(RechercheActivity.this, ChatActivity.class);
				System.out.println("new intent");
				i.putExtra(IDUSER, user);
				i.putExtra(Uuser, repeche);
				System.out.println("putextra");
				startActivity(i);
				System.out.println("Start activity");
			}// fin méthode onClick
		}// fin classe onClickLister
		);// fin paramétrage méthode setOnClickListener);

		btnRech = (Button) findViewById(R.id.btnRech);
		btnRech.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				MyAccesDB adb = new MyAccesDB(RechercheActivity.this);
				adb.execute();
			}// fin méthode onClick
		}// fin classe onClickLister
		);// fin paramétrage méthode setOnClickListener

		btnChat = (Button) findViewById(R.id.btnChat);
		btnChat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (resUser.getText().equals(null) || resUser.getText().equals("")) {

					 }// Fin if
				else {

					Intent i = new Intent(RechercheActivity.this, ChatActivity.class);
					i.putExtra(IDUSER, user);
					i.putExtra(Uuser, repeche);
					startActivity(i);
				}// Fin else
			}// Fin onClick
		}// Fin new OnClick
		);// Fin set onClickListenr

	}

	
	private DialogInterface.OnClickListener listener =new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
				
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.recherche, menu);
		return true;
	}

	class MyAccesDB extends AsyncTask<String, Integer, Boolean> {
		public MyAccesDB(RechercheActivity pActivity) {

			link(pActivity);
		}

		private void link(RechercheActivity pActivity) {

		}

		protected void onPreExecute() {
			Toast.makeText(RechercheActivity.this, "connexion en cours",
					Toast.LENGTH_SHORT).show();

		}

		@Override
		protected Boolean doInBackground(String... arg0) {

			Connection con = new DBConnection().getConnection();
			if (con == null) {
				return false;
			}
			UtilisateurDB.setConnection(con);
			try {
				String pseudo = rechUser.getText().toString();
				user = new UtilisateurDB(pseudo);
				user.rech_user();
			} catch (Exception e) {
			}

			return true;

		}

		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			resUser.setText(user.getPseudo() + " " + user.getNumtel());

		}
	}

}
