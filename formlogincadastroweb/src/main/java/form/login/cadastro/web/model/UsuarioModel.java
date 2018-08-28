package form.login.cadastro.web.model;

import java.util.List;

import form.login.cadastro.web.persistence.UsuarioService;
import form.login.cadastro.web.util.UsuarioModelException;
import form.login.cadastro.web.util.UsuarioServiceException;
import form.login.cadastro.web.util.UsuarioServiceFactory;
import form.login.cadastro.web.vo.Usuario;

/**
 * Class to implement the role of the system and the access to the data.
 * 
 * @author Baracho
 * 
 * @since June 09, 2018
 *
 * @version 1.0
 *
 */
public class UsuarioModel {

	/**
	 * Instance variables
	 */

	private UsuarioService usuarioService;

	/**
	 * Default constructor
	 */
	public UsuarioModel() {
		// TODO Auto-generated constructor stub

		this.usuarioService = UsuarioServiceFactory.getInstanceUsuarioService();
	}

	/**
	 * Class operations
	 */

	/**
	 * @param usuario
	 *            the usuario to set
	 * @throws UsuarioModelException
	 *             the UsuarioModelException to throws
	 */
	public void saveOrUpdate(Usuario usuario) throws UsuarioModelException {

		try {

			this.usuarioService.saveOrUpdate(usuario);

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioModelException();
		}
	}

	/**
	 * @return the usuarios
	 * @throws UsuarioModelException
	 *             the UsuarioModelException to throws
	 */
	public List<Usuario> list() throws UsuarioModelException {

		List<Usuario> usuarios = null;

		try {

			usuarios = this.usuarioService.list();

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			usuarios = null;

			e.printStackTrace();

			throw new UsuarioModelException();
		}

		return usuarios;
	}

	/**
	 * @param usuario
	 * @return the flag
	 * @throws UsuarioModelException
	 *             the UsuarioModelException to throws
	 */
	public Boolean delete(Usuario usuario) throws UsuarioModelException {

		Boolean flag = null;

		try {

			flag = this.usuarioService.delete(usuario);

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			flag = null;

			e.printStackTrace();

			throw new UsuarioModelException();
		}

		return flag;
	}

	/**
	 * @return the id
	 * @throws UsuarioModelException
	 */
	public Integer search() throws UsuarioModelException {

		Integer id = null;

		try {

			id = this.usuarioService.search();

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			id = null;

			e.printStackTrace();

			throw new UsuarioModelException();
		}

		return id;
	}

	/**
	 * @param usuario
	 * @throws UsuarioModelException
	 *             the UsuarioModelException to throws
	 */
	public void search(Usuario usuario) throws UsuarioModelException {

		try {

			this.usuarioService.search(usuario);

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			usuario = null;

			e.printStackTrace();

			throw new UsuarioModelException();
		}
	}

	/**
	 * @param nome
	 * @return the usuarios
	 * @throws UsuarioModelException
	 */
	public List<Usuario> search(String nome) throws UsuarioModelException {

		List<Usuario> usuarios = null;

		try {

			usuarios = this.usuarioService.search(nome);

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			usuarios = null;

			e.printStackTrace();

			throw new UsuarioModelException();
		}

		return usuarios;
	}

	/**
	 * @param usuario
	 * @throws UsuarioModelException
	 *             the UsuarioModelException to throws
	 */
	public void authenticateUsuario(Usuario usuario) throws UsuarioModelException {

		try {

			this.usuarioService.authenticateUsuario(usuario);

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			throw new UsuarioModelException();
		}
	}

	/**
	 * @param login
	 * @return the usuario
	 * @throws UsuarioModelException
	 */
	public Usuario validateLogin(String login) throws UsuarioModelException {

		Usuario usuario = null;

		try {

			usuario = this.usuarioService.validateLogin(login);

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			usuario = null;

			e.printStackTrace();

			throw new UsuarioModelException();
		}

		return usuario;
	}

	/**
	 * @param login
	 * @param id
	 * @return the usuario
	 * @throws UsuarioModelException
	 *             the UsuarioModelException to throws
	 */
	public Usuario validateLogin(String login, Integer id) throws UsuarioModelException {

		Usuario usuario = null;

		try {

			usuario = this.usuarioService.validateLogin(login, id);

		} catch (UsuarioServiceException e) {
			// TODO Auto-generated catch block

			usuario = null;

			e.printStackTrace();

			throw new UsuarioModelException();
		}

		return usuario;
	}
}
