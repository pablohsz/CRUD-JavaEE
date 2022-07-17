package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	// **CRUD READ**//
	public ArrayList<JavaBeans> listarContatos() {
		// Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "SELECT * FROM contatos ORDER BY nome;";
		try {
			Connection con = criarConexao();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			String idcon, nome, fone, email;
			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variáveis de apoio que recebem os dados do banco
				idcon = rs.getString("idcon");
				nome = rs.getString("nome");
				fone = rs.getString("fone");
				email = rs.getString("email");
				// populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;

		} catch (Exception e) {
			System.out.print(e);
			return null;
		}

	}

}
