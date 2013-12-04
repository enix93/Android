package projet.yankiba;

import projet.yankiba.MainActivity.CreationDB;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends Activity {
	private  Button recherche = null;
	private  Button chat = null;
	private TextView greeting=null;
	private UtilisateurDB Utilisateur =null;
	private Intent i=null;
	public static final String Uuser="Utilisateur";
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
	    	    
	       }//Il n'est normalement pas necessaire de vérifier le contenu de l'intent car on ne peut acceder à cette page que si l'on a un compte mais on est jamais trop prudent
	   
	       chat.setOnClickListener(new OnClickListener() {
	  			public void onClick(View v) {
	  				i = new Intent(MainPage.this, ChatActivity.class);
	  				i.putExtra(Uuser,Utilisateur);
						startActivity(i);
	  				
	  			}
	  			});
	       
	      recherche.setOnClickListener(new OnClickListener() {
	  			public void onClick(View v) {
	  			 i = new Intent(MainPage.this,RechercheActivity.class);
				     i.putExtra(Uuser,Utilisateur);
				     startActivity(i);
	  				
	  			}
	  			});
	     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}

}
