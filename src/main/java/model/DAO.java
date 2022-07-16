package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO {

	/*** MÉTODO DE CONEXÃO ***/
	private static final String URL = "jdbc:postgresql://localhost:5432/projetoweb";
	private static final String USER = "postgres";
	private static final String PASS = "520090";

	public static Connection criarConexao() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection(URL, USER, PASS);
		if (conecta != null) {
			System.out.print("Conexão efetuada com sucesso!");
			return conecta;
		}
		return null;
	}

	// **CRUD CREATE**//
	public void inserirContato(JavaBeans contato) {
		String create = "INSERT INTO public.contatos( nome, fone, email) VALUES (?, ?, ?);";
		try {
			Connection con = criarConexao();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parâmetros pelo conteúdo das variáveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query
			pst.executeUpdate();
			// Encerrar conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
