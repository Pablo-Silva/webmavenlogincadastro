package form.login.cadastro.web.util;

import static form.login.cadastro.web.util.ConstantsStatic.END_OF_THE_PROGRAMA;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import form.login.cadastro.web.model.UsuarioModel;
import form.login.cadastro.web.vo.Usuario;

/**
 * Program to generate the data to the Usuario entity.
 * 
 * @author Baracho
 * 
 * @since May 08, 2018
 * 
 * @version 1.0
 *
 */
public class GenerateDataCadastroPessoaWeb {

	/**
	 * 
	 */
	public GenerateDataCadastroPessoaWeb() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Variables declaration

		UsuarioModel usuarioMaintenance = null;

		Connection connection = null;

		Statement statement = null;

		ResultSet resultSet = null;

		String query = null;

		List<Usuario> usuarios = null;

		Iterator<Usuario> iterator = null;

		Usuario usuario = null;

		String nome = null;

		// Data processing

		try {

			connection = OracleConnectionFactory.getConnection();

			query = "SELECT ";

			query += "P.FIRST_NAME, ";

			query += "P.MIDDLE_NAME, ";

			query += "P.LAST_NAME, ";

			query += "P.DOB, P.GENDER, ";

			query += "P.EMAIL, ";

			query += "P.STATUS, ";

			query += "U.LOGIN, ";

			query += "U.PASSWORD ";

			query += "FROM TB_PERSON P INNER JOIN TB_USER U ";

			query += "ON P.ID_TB_USER = U.ID_TB_USER AND U.ID_TB_USER < 5001";

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			resultSet = statement.executeQuery(query);

			if (resultSet.last()) {

				resultSet.beforeFirst();

				usuarios = new ArrayList<Usuario>();

				while (resultSet.next()) {

					usuario = new Usuario();

					nome = resultSet.getString("FIRST_NAME");

					if (resultSet.getString("MIDDLE_NAME") != null) {

						nome += " " + resultSet.getString("MIDDLE_NAME");
					}

					if (resultSet.getString("LAST_NAME") != null) {

						nome += " " + resultSet.getString("LAST_NAME");
					}

					usuario.setNome(nome);

					if (resultSet.getDate("DOB") != null) {

						usuario.setDataNascimento(new Date(resultSet.getDate("DOB").getTime()));
					}

					if (resultSet.getString("GENDER") != null) {

						usuario.setSexo(new Character(resultSet.getString("GENDER").charAt(0)));
					}

					if (resultSet.getString("EMAIL") != null) {

						usuario.setEmail(resultSet.getString("EMAIL"));
					}

					if (resultSet.getInt("STATUS") != 0) {

						if (resultSet.getInt("STATUS") == 1) {

							usuario.setStatus(new Boolean(true));

						} else {

							if (resultSet.getInt("STATUS") == 2) {

								usuario.setStatus(new Boolean(false));
							}
						}
					}

					usuario.setLogin(resultSet.getString("LOGIN"));

					usuario.setSenha(resultSet.getString("PASSWORD"));

					usuarios.add(usuario);
				}
			}

		} catch (SQLException e) {
			// TODO: handle exception

			e.printStackTrace();

			usuarios = null;

		} finally {

			if (statement != null) {

				try {

					statement.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (resultSet != null) {

				try {

					resultSet.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (usuarios != null && usuarios.size() > 0) {

			usuarioMaintenance = new UsuarioModel();

			iterator = usuarios.iterator();

			while (iterator.hasNext()) {

				usuario = iterator.next();

				try {

					usuarioMaintenance.saveOrUpdate(usuario);

				} catch (UsuarioModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println(usuario.toString());
			}
		}

		System.out.println(String.format("\n %s", END_OF_THE_PROGRAMA));
	}

}
