package form.login.cadastro.web.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import form.login.cadastro.web.util.UsuarioServiceException;
import form.login.cadastro.web.vo.Usuario;

/**
 * Class to implement the interface between the system and the data access layer
 * to the Usuario entity.
 * 
 * @author Baracho
 * 
 * @since June 09, 2018
 * 
 * @version 2.0
 * 
 */

public class UsuarioService implements IUsuarioDAO {

	/**
	 * Instance variable
	 */

	private UsuarioDAO usuarioDAO;

	/**
	 * Default constructor
	 */

	public UsuarioService() {

		this.usuarioDAO = new UsuarioDAO();
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Connection connection) {
		this.usuarioDAO.setConnection(connection);
	}

	/**
	 * Class operations
	 */

	public void saveOrUpdate(Usuario usuario) throws UsuarioServiceException {
		// TODO Auto-generated method stub

		try {

			this.usuarioDAO.saveOrUpdate(usuario);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioServiceException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see form.login.persistence.usuario.IUsuarioDAO#list()
	 */
	public List<Usuario> list() throws UsuarioServiceException {
		// TODO Auto-generated method stub

		List<Usuario> usuarios = null;

		try {

			usuarios = this.usuarioDAO.list();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			usuarios = null;

			e.printStackTrace();

			throw new UsuarioServiceException();
		}

		return usuarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see form.login.persistence.usuario.IUsuarioDAO#excluir(
	 * form.login.cadastro.web.model.Usuario)
	 */
	public Boolean delete(Usuario usuario) throws UsuarioServiceException {
		// TODO Auto-generated method stub

		Boolean flag = null;

		try {

			flag = this.usuarioDAO.delete(usuario);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioServiceException();
		}

		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see form.login.persistence.usuario.IUsuarioDAO#pesquisar()
	 */
	public Integer search() throws UsuarioServiceException {
		// TODO Auto-generated method stub

		Integer id = null;

		try {

			id = this.usuarioDAO.search();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioServiceException();
		}

		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see form.login.persistence.usuario.IUsuarioDAO#search(
	 * form.login.cadastro.web.vo. Usuario)
	 */
	public void search(Usuario usuario) throws UsuarioServiceException {
		// TODO Auto-generated method stub

		try {

			this.usuarioDAO.search(usuario);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioServiceException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see form.login.persistence.usuario.IUsuarioDAO#pesquisar(
	 * java.lang.String)
	 */
	public List<Usuario> search(String nome) throws UsuarioServiceException {
		// TODO Auto-generated method stub

		List<Usuario> usuarios = null;

		try {

			usuarios = this.usuarioDAO.search(nome);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioServiceException();
		}

		return usuarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see form.login.persistence.usuario.IUsuarioDAO#autenticarUsuario(
	 * form.login.cadastro.web.model .Usuario)
	 */
	public void authenticateUsuario(Usuario usuario) throws UsuarioServiceException {
		// TODO Auto-generated method stub

		try {

			this.usuarioDAO.authenticateUsuario(usuario);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioServiceException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.persistence.usuario.IUsuarioDAO#validateLogin(java.lang.String)
	 */
	public Usuario validateLogin(String login) throws UsuarioServiceException {
		// TODO Auto-generated method stub

		try {

			return this.usuarioDAO.validateLogin(login);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioServiceException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * form.login.persistence.usuario.IUsuarioDAO#validateLogin(
	 * java.lang.String, java.lang.Integer)
	 */
	public Usuario validateLogin(String login, Integer id) 
			throws UsuarioServiceException {
		// TODO Auto-generated method stub

		Usuario usuario = null;

		try {

			usuario = this.usuarioDAO.validateLogin(login, id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioServiceException();
		}

		return usuario;
	}
}