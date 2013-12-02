package projet.yankiba;
import java.sql.Connection;
import myconnections.DBConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;/*
import android.widget.*;
public class RechercheActivity extends Activity {
	EditText rechUser = null;
	EditText resUser = null;
	Button btnChat = null;
	Button btnRech = null;
	UtilisateurDB user = null;
	public static final String IDUSER = "utilisateurDB";

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
			// Statement stmt = dbConnect.createStatement();
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
			// Toast.makeText(RechercheActivity.this, resultat, Toast.LENGTH_LONG).show();
			resUser.setText(user.getPseudo() + " " + user.getNumtel());
		}
	}
}*/