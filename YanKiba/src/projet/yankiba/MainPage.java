package projet.yankiba;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends Activity {
	private  Button recherche = null;
	private  Button chat = null;
	private TextView greeting=null;
	private UtilisateurDB Utilisateur =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		greeting =(TextView) findViewById(R.id.Welcome);
		chat=(Button)findViewById(R.id.chat);
		recherche=(Button)findViewById(R.id.recherche);
		
		Intent intent = getIntent();
	     
	       if (intent != null) {
	    	  Utilisateur=(UtilisateurDB)intent.getParcelableExtra(MainActivity.Uuser);
	    	  greeting.setText("Bienvenue  "+Utilisateur.getPseudo());
	    	    
	       }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}

}
