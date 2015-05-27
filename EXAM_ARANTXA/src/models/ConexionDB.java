package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
	//DATOS DE LA CONEXION
	static final String CONTROLADOR_MYSQL ="com.mysql.jdbc.Driver";

	//DATOS DE LA BASE DE DATOS
	private String host;
	private String bbdd;
	private String user;
	private String pass;
	
	//CONEXION
	Connection conexion; //MANEJA LA CONEXION

	public ConexionDB(String HOST, String BBDD, String USER, String PASS){
		this.host=HOST;
		this.bbdd=BBDD;
		this.user=USER;
		this.pass=PASS;
	}
	
	public boolean connectDB(){
		try{
			//LO PRIMERO ES CARGAR EL CONTROLADOR DE MYSQL EL CUAL AUTOMATICAMENTE DE REGISTRA
			Class.forName(CONTROLADOR_MYSQL);
			//CONECTAMOS A LA BASE DE DATOS
			conexion = DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.bbdd,this.user,this.pass);
		}
		catch (SQLException excepcionSql)	
		{
			excepcionSql.printStackTrace();
		}
		catch(ClassNotFoundException claseNoEncontrada)
		{
			claseNoEncontrada.printStackTrace();
			return false;			
		}
		return true;			
	}

    public Connection getConexion() {
	  return conexion;
	  }
}

