package projet.yankiba;

import java.sql.*;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class UtilisateurDB extends Utilisateur implements CRUD,Parcelable {

	 protected static Connection dbConnect=null;

	 
	public UtilisateurDB(){
		
	}
	public UtilisateurDB (String pseudo){
		super(0,0,pseudo,0);
	}
	
	public UtilisateurDB(int id_user, int numtel, int prefixe, String pseudo) {
		super( numtel, prefixe, pseudo,id_user);
		
		
	}

	public UtilisateurDB(int numtel,int prefixe,String pseudo){
		super(numtel,prefixe,pseudo,0);
	}
	  
		public UtilisateurDB(int numtel,int prefixe){
			super(numtel,prefixe,"",0);
		}
	
	public UtilisateurDB(int id_user){
		super(0,0,"",0);
		this.id_user=id_user;
	}

	  public static void setConnection(Connection nouvdbConnect) {
	      dbConnect=nouvdbConnect;
	   }
	  public void rech_user() throws Exception {
			String req = "{?=call rech_user(?)}";

			CallableStatement cstmt = null;
			try {
				cstmt = dbConnect.prepareCall(req);
				cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
				cstmt.setString(2, this.pseudo);
				cstmt.executeQuery();
				ResultSet rs = (ResultSet) cstmt.getObject(1);
				if (rs.next()) {
					this.id_user = rs.getInt("ID_USER");
					this.numtel = rs.getInt("NUMTEL");
					this.prefixe = rs.getInt("PREFIXE");
					this.pseudo = rs.getString("PSEUDO");

				} else {
					throw new Exception("Utilisateur Inconnu");
				}
			} catch (Exception e) {
				throw new Exception("Erreur de lecture " + e.getMessage());
			} finally {
				try {
					cstmt.close();
				} catch (Exception e) {

				}
			}

		}
	  @Override
	public void create() throws Exception{
		  CallableStatement   cstmt=null;
		  try{
			Log.d("on est icieeeeee","lol");
	        String req=" call create_user(?,?,?,?)";
	        cstmt=dbConnect.prepareCall(req);
	        cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
	        cstmt.setInt(2, numtel);
	        cstmt.setInt(3, prefixe);
	        cstmt.setString(4,pseudo);
	        cstmt.executeUpdate();
	        this.id_user=cstmt.getInt(1);
	       
	        
	  }
		  catch(Exception e ){
	          
              throw new Exception("Erreur de création "+e.getMessage());
           }
     finally{//effectué dans tous les cas 
          try{
            cstmt.close();
          }
          catch (Exception e){}
      }
	
  }
	  @Override
	public void delete()throws Exception{
			
          CallableStatement cstmt =null;
	   try{
	     String req = "call delete_user(?)";
	     cstmt = dbConnect.prepareCall(req);
	     cstmt.setInt(1,id_user);
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
	  @Override
	public void update() throws Exception{
	        CallableStatement cstmt=null;

	    try{
		     String req = "call update_user(?,?,?)";
		     cstmt=dbConnect.prepareCall(req);
	         PreparedStatement pstm = dbConnect.prepareStatement(req);
		     cstmt.setInt(1,numtel);
		     cstmt.setInt(2, prefixe);
		     cstmt.setString(3, pseudo);
		     cstmt.executeUpdate();
	            
	    }

		  catch(Exception e){
		  	
	                throw new Exception("Erreur de mise à jour "+e.getMessage());
	             }
	          finally{//effectué dans tous les cas 
	            try{
	              cstmt.close();
	            }
	            catch (Exception e){}
	        }
	    }
	  @Override
		public void read ()throws Exception{
				
				String req = "{?=call read_user(?,?)}";
				    
			        CallableStatement cstmt=null;
			        try{
			        cstmt=dbConnect.prepareCall(req);
			        Log.d("essai","lol");
			        cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			        cstmt.setInt(2,prefixe);
					cstmt.setInt(3, numtel);
				    cstmt.executeQuery();
				    Log.d("essai2","lol");
			        ResultSet rs=(ResultSet)cstmt.getObject(1);
			        if(rs.next()){
			        	Log.d("essai3","lol");
				     	this.id_user=rs.getInt("ID_USER");
				     	Log.d("essai4",Integer.toString(id_user)+"lol");
				     	this.numtel=rs.getInt("NUMTEL");
				     	Log.d("essai5",Integer.toString(numtel)+"lol");
				     	this.prefixe=rs.getInt("PREFIXE");
				     	Log.d("essai6",Integer.toString(prefixe)+"lol");
				     	this.pseudo=rs.getString("PSEUDO");
				     	Log.d("essai7",pseudo+"lol");
				        
			              }
				      else { 
				             throw new Exception("ID Inconnu");
				      }

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
	//int id_user, int numtel, int prefixe, String pseudo
		
		@Override
		 public int describeContents() {
		   //On renvoie 0, car notre classe ne contient pas de FileDescriptor
		   return 0;
		 }
		 @Override
		 public void writeToParcel(Parcel dest, int flags) {
		   // On ajoute les objets dans l'ordre dans lequel on les a déclarés
		   dest.writeInt(id_user);
		   dest.writeInt(numtel);
		   dest.writeInt(prefixe);
		   dest.writeString(pseudo);
		 }
		 public static final Parcelable.Creator<UtilisateurDB> CREATOR = new Parcelable.Creator<UtilisateurDB>() {
		   @Override
		   public UtilisateurDB createFromParcel(Parcel source) {
		     return new UtilisateurDB(source);
		   }
		   @Override
		   public UtilisateurDB[] newArray(int size) {
		     return new UtilisateurDB[size];
		   }
		 };
		 public UtilisateurDB(Parcel in) {
		 	  id_user = in.readInt();
		 	  numtel = in.readInt();
		 	  prefixe = in.readInt();
		 	  pseudo=in.readString();
		 	}
}
