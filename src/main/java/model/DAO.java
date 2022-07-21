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
			System.out.println("Conexão efetuada com sucesso!");
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

	// **CRUD READ**//
	// selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String select = "SELECT * FROM contatos WHERE idcon = ?;";
		try {
			Connection con = criarConexao();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setInt(1, Integer.parseInt(contato.getIdcon()));
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// setar as variáveis JavaBeans
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// editar o contato
	public void alterarContato(JavaBeans contato) {
		String update = "UPDATE contatos SET nome = ?, fone = ?, email= ? WHERE idcon = ?;";
		try {
			Connection con = criarConexao();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setInt(4, Integer.parseInt(contato.getIdcon()));
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// **CRUD DELETE**//
	public void deletarContato(JavaBeans contato) {
		String delete = "DELETE FROM contatos WHERE idcon = ?; ALTER SEQUENCE contatos_idcon_seq RESTART WITH ?;";
		try {
			Connection con = criarConexao();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, Integer.parseInt(contato.getIdcon()));
			pst.setInt(2, Integer.parseInt(contato.getIdcon()));
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
