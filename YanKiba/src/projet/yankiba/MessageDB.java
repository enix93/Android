package projet.yankiba;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import projet.yankiba.UtilisateurDB;

public class MessageDB extends Message implements CRUD {
	protected static Connection dbConnect = null;
	protected Son son;
	protected Emote emote;

	public MessageDB(String contenu, Utilisateur expediteur,
			Utilisateur destinataire, int id_message) {
		super(id_message, contenu, expediteur, destinataire);
	}

	public MessageDB(int id_message, String contenu, Utilisateur expediteur,
			Utilisateur destinataire, Son son, Emote emote) {
		super(id_message, contenu, expediteur, destinataire);
		this.son = son;
		this.emote = emote;
	}

	public MessageDB() {
		this.id_message=0;
		this.contenu=null;
		this.destinataire=null;
		this.expediteur=null;
		this.emote=null;
		this.son=null;
	}

	public static void setConnection(Connection nouvdbConnect) {
		dbConnect = nouvdbConnect;
	}

	@Override
	public void create() throws Exception {
		CallableStatement cstmt = null;
		try {
			String req = " call create_message(?,?,?,?,?,?)";
			cstmt = dbConnect.prepareCall(req);
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.setInt(2, expediteur.getId_user());
			cstmt.setInt(3, destinataire.getId_user());
			cstmt.setString(4, contenu);
			//cstmt.setInt(5, emote.getId_emote());
			//cstmt.setInt(6, son.getId_son());
			cstmt.setObject(5, null);
			cstmt.setObject(6, null);
			cstmt.executeUpdate();
			this.id_message = cstmt.getInt(1);
		} catch (Exception e) {
			System.out.println("Erreur de création" + e);
		} finally {// effectué dans tous les cas
			try {
				cstmt.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void read() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		/*
		 * Comme on ne peut pas update un sms(par soucis de logique), mais qu'il
		 * faut absolument avoir un update suite à l'implements CRUD, on
		 * utilise une update vide
		 */}

	@Override
	public void delete() throws Exception {
		CallableStatement cstmt = null;
		try {
			String req = "call delete_message(?)";
			cstmt = dbConnect.prepareCall(req);
			cstmt.setInt(1, id_message);
			cstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception("Erreur d'effacement " + e.getMessage());
		} finally {// effectué dans tous les cas
			try {
				cstmt.close();
			} catch (Exception e) {
			}
		}

	}

	public static ArrayList<MessageDB> rechmessage(String rechmess)	throws Exception {
		ArrayList<MessageDB> plusieurs = new ArrayList<MessageDB>();
		String req = "{?=call read_message(?)}";
		CallableStatement cstmt = null;
		try {
			cstmt = dbConnect.prepareCall(req);
			cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.setString(2, rechmess);
			cstmt.executeQuery();
			ResultSet rs = (ResultSet) cstmt.getObject(1);
			boolean trouve = false;
			while (rs.next()) {
				trouve = true;
				int id_message = rs.getInt("ID_MESSAGE");
				Utilisateur destinataire = new Utilisateur();
				Utilisateur expediteur = new Utilisateur();
				expediteur.id_user = rs.getInt("EXPEDITEUR");
				destinataire.id_user = rs.getInt("DESTINATAIRE");
				String contenu = rs.getString("CONTENU");
				Son son = new Son();
				son.id_son = rs.getInt("SON");
				Emote emote = new Emote();
				emote.id_emote = rs.getInt("EMOTE");
				plusieurs.add(new MessageDB(id_message, contenu, expediteur, destinataire, son, emote));
				// int id_message,String contenu,Utilisateur
				// expediteur,Utilisateur destinataire,Son son, Emote emote }
				if (!trouve)
					throw new Exception("nom inconnu");
				else
					return plusieurs;
			}
		} catch (Exception e) {
			throw new Exception("Erreur de lecture " + e.getMessage());
		} finally {// effectué dans tous les cas
			try {
				cstmt.close();
			} catch (Exception e) {
			}
		}
		return plusieurs;
	}
	
	public static ArrayList<MessageDB> affMessage(int exp, int dest) throws Exception{
		
		ArrayList<MessageDB> liste = new ArrayList<MessageDB>();
		String req = "{? = call liste_message(?,?) }";
		CallableStatement cstmt = null;
		try{
			cstmt = dbConnect.prepareCall(req);
			cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.setInt(2,dest);
			cstmt.setInt(3, exp);
			cstmt.executeQuery();
			ResultSet rs = (ResultSet) cstmt.getObject(1);
			while(rs.next()){
				
				int id_mess = rs.getInt("ID_MESSAGE");
				UtilisateurDB expediteur = new UtilisateurDB(rs.getInt("EXPEDITEUR"));
				UtilisateurDB destinataire = new UtilisateurDB(rs.getInt("DESTINATAIRE"));
				String contenu = rs.getString("CONTENU");
				Son son = new Son();
				son.id_son = rs.getInt("SON");
				Emote emote = new Emote();
				emote.id_emote = rs.getInt("EMOTE");
				MessageDB mess = new MessageDB(id_mess, contenu, destinataire, expediteur, son, emote);
				liste.add(mess);
			}
		}catch(Exception e){
			throw new Exception("Erreur de lecture " + e.getMessage());
		}finally{
			try{
				cstmt.close();
			}catch(Exception e){
				
			}
		}
		
		return liste;
		
	}
}