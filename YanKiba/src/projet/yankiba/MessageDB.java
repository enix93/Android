package projet.yankiba;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MessageDB extends Message implements CRUD {
	 protected int id_message;
	 protected static Connection dbConnect=null;
	 protected Son son;
	 protected Emote emote;
	 
	public MessageDB(String contenu, Utilisateur expediteur,
			Utilisateur destinataire,int id_message) {
		super(id_message,contenu, expediteur, destinataire);
	
	}
	 public MessageDB(int id_message,String contenu,Utilisateur expediteur,Utilisateur destinataire,Son son, Emote emote){
		 super(id_message,contenu,expediteur,destinataire);
		 this.son=son;
		 this.emote=emote;
	 }
	 
	 public static void setConnection(Connection nouvdbConnect) {
	      dbConnect=nouvdbConnect;
	   }
	 
	 public void create() throws Exception{
		 CallableStatement   cstmt=null;
		 try{
			 String req=" call create_message(?,?,?,?,?,?,?)";
		     cstmt=dbConnect.prepareCall(req);
		     cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
		     cstmt.setInt(2,expediteur.getId_user());
		     cstmt.setInt(3,destinataire.getId_user());
		     cstmt.setString(4, contenu);
		     cstmt.setInt(5,emote.getId_emote());
		     cstmt.setInt(6, son.getId_son());
		     cstmt.executeUpdate();
		     
		 }
		 
		 catch(Exception e){
			 System.out.println("Erreur de création"+e);
			
			 
		 }
		 finally{//effectué dans tous les cas 
	          try{
	            cstmt.close();
	          }
	          catch (Exception e){}
	      }
	
	 }
	 public void delete()throws Exception{
			
         CallableStatement cstmt =null;
	   try{
	     String req = "call delete_message(?)";
	     cstmt = dbConnect.prepareCall(req);
	     cstmt.setInt(1,id_message);
	     cstmt.executeUpdate();
	     	     
	     }	
	   catch (Exception e){
	     	
              throw new Exception("Erreur d'effacement "+e.getMessage());
           }
         finally{//effectué dans tous les cas 
          try{
            cstmt.close();
          }
          catch (Exception e){}
        }
 	}
	 
   public void update(){
	   /*Comme on ne peut pas update un sms(par soucis de logique),
	     mais qu'il faut absolument avoir un update suite à l'implements CRUD,
	    * on utilise une update vide
	    */
   }
	
   public static ArrayList<MessageDB> rechmessage(String rechmess)throws Exception{
	   ArrayList<MessageDB>plusieurs = new ArrayList<MessageDB>();
	   String req = "{?=call read_message(?)}";
	   CallableStatement cstmt=null;
	   try{
		   cstmt = dbConnect.prepareCall(req);
           cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
           cstmt.setString(2, rechmess);
           cstmt.executeQuery();
           ResultSet rs=(ResultSet)cstmt.getObject(1);
	       boolean trouve=false;
	       while(rs.next()){
               trouve=true;
               int id_message =rs.getInt("ID_MESSAGE");
               Utilisateur destinataire=new Utilisateur();
               Utilisateur expediteur=new Utilisateur();
               expediteur.id_user=rs.getInt("EXPEDITEUR");
               destinataire.id_user=rs.getInt("DESTINATAIRE");
               String contenu=rs.getString("CONTENU");
               Son son=new Son();
               son.id_son=rs.getInt("SON");
               Emote emote =new Emote();
               emote.id_emote=rs.getInt("EMOTE");
               plusieurs.add(new MessageDB(id_message,contenu,expediteur,destinataire,son,emote));   
               //int id_message,String contenu,Utilisateur expediteur,Utilisateur destinataire,Son son, Emote emote
               
	       }
	       if(!trouve)throw new Exception("nom inconnu");
           else return plusieurs;
	     }
	     catch(Exception e){
		
             throw new Exception("Erreur de lecture "+e.getMessage());
          }
         finally{//effectué dans tous les cas 
         try{
           cstmt.close();
         }
         catch (Exception e){}
     }
	   
	   
   }
   
   public void read(){
	   
   }
   
	 
}
