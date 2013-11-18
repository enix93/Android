package projet.yankiba;

import java.sql.*;
import java.util.*;

public class UtilisateurDB extends Utilisateur implements CRUD {

	 protected static Connection dbConnect=null;
	 protected int id_user;
	 
	
	
	public UtilisateurDB(int id_user, int numtel, int prefixe, String pseudo) {
		super( numtel, prefixe, pseudo);
		this.id_user=id_user;
		
	}

	public UtilisateurDB(int numtel,int prefixe,String pseudo){
		super(numtel,prefixe,pseudo);
	}
	
	public UtilisateurDB(int id_user){
		super(0,0,"");
		this.id_user=id_user;
	}

	  public static void setConnection(Connection nouvdbConnect) {
	      dbConnect=nouvdbConnect;
	   }
	  public void create() throws Exception{
		  CallableStatement   cstmt=null;
		  try{
	        
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
	  public void read ()throws Exception{
			
			String req = "{?=call read_user(?)}";
		        
		        CallableStatement cstmt=null;
		        try{
		        cstmt=dbConnect.prepareCall(req);
		        cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
			cstmt.setInt(2,id_user);
			cstmt.executeQuery();
		        ResultSet rs=(ResultSet)cstmt.getObject(1);
		        if(rs.next()){
			     	this.id_user=rs.getInt("ID_USER");
			     	this.numtel=rs.getInt("NUMTEL");
			     	this.prefixe=rs.getInt("PREFIXE");
			     	this.pseudo=rs.getString("PSEUDO");
			        
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
}
