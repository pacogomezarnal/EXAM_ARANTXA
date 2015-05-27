package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Clase que comprueba el codigo de entrada
public class IngresoLaby {
	//CONSULTA A LA BASE DE DATOS
	private final static String CHECK_SEL="SELECT cod FROM cod WHERE id=";
	private static String COD_COL="cod";
	
	//Conexion
	private Connection conexion;// maneja la conexió
	Statement instruccion = null ;
	ResultSet conjuntoResultados = null;

	private ArrayList<String> cadete;
	;
	
	public IngresoLaby(ConexionDB conexion) {
		//Obtenemos la conexion de datos
		this.conexion = conexion.getConexion();
		cadete =  new ArrayList<String>();
	}
	
	public String getCod(int id,String apellido){
		try{
			String cod="";
			instruccion = this.conexion.createStatement();
			conjuntoResultados = instruccion.executeQuery(CHECK_SEL+String.valueOf(id));
			
			//Listaremos por pantalla los datos
			while(conjuntoResultados.next() ) {
				if(apellido.equals(conjuntoResultados.getString(COD_COL))) cod="CODIGO CORRECTO ENHORABUENA. LLAMA A TU INSTRUCTOR";
				else return cod="CODIGO INCORRECTO COMPRUEBA TODOS LOS PASOS";
			}// fin de while
			return cod;
		}
		catch( SQLException excepcionSql ) 
		{
			excepcionSql.printStackTrace();
			return "ERROR EN LA CONEXION. LLAMA A TU INSTRUCTOR";
		}
		finally{
			try{
				instruccion.close();
				conjuntoResultados.close();
				conexion.close();
			}
			catch( SQLException excepcionSql ) 
			{
				excepcionSql.printStackTrace();
			}
		}	

		
	}

}
