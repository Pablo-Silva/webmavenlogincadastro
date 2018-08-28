package form.login.cadastro.web.persistence;

import java.util.List;

import form.login.cadastro.web.vo.Usuario;

/**
 * Interface to define the operations against to the database to the Usuario
 * entity.
 * 
 * @author Baracho
 * 
 * @since June 09, 2018
 * 
 * @version 3.0
 * 
 */

interface IUsuarioDAO {

	public void saveOrUpdate(Usuario usuario) throws Throwable;

	public List<Usuario> list() throws Throwable;

	public Boolean delete(Usuario usuario) throws Throwable;

	public Integer search() throws Throwable;

	public void search(Usuario usuario) throws Throwable;

	public List<Usuario> search(String nome) throws Throwable;

	public void authenticateUsuario(Usuario usuario) throws Throwable;

	public Usuario validateLogin(String login) throws Throwable;

	public Usuario validateLogin(String login, Integer id) throws Throwable;

}