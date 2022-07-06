package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	/***MÓDULO DE CONEXÃO***/
	private static final String URL = "jdbc:postgresql://localhost:5432/projetoweb";
	private static final String USER = "postgres";
	private static final String PASS = "520090";
	
	public static Connection criarConexao() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection(URL, USER, PASS);
		if (conecta != null) {
			System.out.print("Conexão efetuada com sucesso!");
			return conecta;
		}
		return null;
	}
}
