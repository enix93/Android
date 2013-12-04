package projet.yankiba;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import myconnections.DBConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;

public class ChatActivity extends Activity {

	ListView liste = null;
	EditText editMsg = null;
	Button btnEnvoi = null;
	Button btnRefresh = null;
	int items;
	UtilisateurDB dest = null;
	UtilisateurDB exp = null;
	MessagesAdapter mA;
	ArrayList<MessageDB> listMess = new ArrayList<MessageDB>();
	List<String> messages = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		Intent i = getIntent();
		dest = (UtilisateurDB) i.getParcelableExtra(RechercheActivity.IDUSER);
	    exp =(UtilisateurDB) i.getParcelableExtra(RechercheActivity.Uuser);
	    Log.d("lol","lol"+exp.getPseudo());
		setTitle(dest.getPseudo());
		editMsg = (EditText) findViewById(R.id.editMsg);
		liste = (ListView) findViewById(R.id.listView1);
		mA = new MessagesAdapter(ChatActivity.this, listMess);
		liste.setAdapter(mA);

		btnEnvoi = (Button) findViewById(R.id.btnEnvoi);
		btnEnvoi.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AjoutMessageDB ajoutMess = new AjoutMessageDB(ChatActivity.this);
				ajoutMess.execute();
				editMsg.setText(null);

			}// fin méthode onClick
		}// fin classe onClickLister
		);// fin paramétrage méthode setOnClickListener
		btnRefresh = (Button) findViewById(R.id.btnRefresh);
		btnRefresh.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ListeMessagesDB messDB = new ListeMessagesDB(ChatActivity.this);
				messDB.execute();

			}// fin méthode onClick
		}// fin classe onClickLister
		);// fin paramétrage méthode setOnClickListener
		ListeMessagesDB messDB = new ListeMessagesDB(ChatActivity.this);
		messDB.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}



	private void addItems(MessageDB item) {
		if (item != null) {
			this.mA.add(item);
			this.mA.notifyDataSetChanged();
			items++;
		}
	}

	public void changeItems(ArrayList<MessageDB> list) {
		this.mA.clear();
		items = 0;
		for (MessageDB item : list) {
			addItems(item);
		}
	}

	class AjoutMessageDB extends AsyncTask<String, Integer, Boolean> {

		public AjoutMessageDB(ChatActivity pActivity) {
			link(pActivity);
		}

		private void link(ChatActivity pActivity) {

		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected Boolean doInBackground(String... params) {
			MessageDB newMessage = new MessageDB();
			newMessage.setDestinataire(dest);
			System.out.println(newMessage.getDestinataire().getPseudo());
			newMessage.setExpediteur(exp);
			System.out.println(newMessage.getExpediteur().getPseudo());
			newMessage.setContenu(editMsg.getText().toString());
			System.out.println(newMessage.getContenu());
			newMessage.setId_message(0);
			System.out.println(newMessage.getId_message());

			Connection con = new DBConnection().getConnection();
			MessageDB.setConnection(con);
			try {
				System.out.println("create");
				newMessage.create();
			} catch (Exception e) {
			}

			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			ListeMessagesDB messDB = new ListeMessagesDB(ChatActivity.this);
			messDB.execute();
		}

	}

	class ListeMessagesDB extends AsyncTask<String, Integer, Boolean> {
		ArrayList<MessageDB> mesMessages = new ArrayList<MessageDB>();

		public ListeMessagesDB(ChatActivity pActivity) {
			link(pActivity);
		}

		private void link(ChatActivity pActivity) {

		}

		protected void onPreExecute() {
			Toast.makeText(ChatActivity.this, "connexion en cours",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Boolean doInBackground(String... arg0) {
			Connection con = new DBConnection().getConnection();
			if (con == null) {
				return false;
			}
			MessageDB.setConnection(con);
			try {
				int id_dest = dest.getId_user();
				int id_exp = exp.getId_user();
				MessageDB mess = new MessageDB();
				mesMessages = mess.affMessage(id_exp, id_dest);
			} catch (Exception e) {

			}
			return true;
		}

		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			changeItems(mesMessages);
		}
	}
}
