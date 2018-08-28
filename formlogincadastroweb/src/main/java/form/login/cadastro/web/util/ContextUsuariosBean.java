package form.login.cadastro.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import form.login.cadastro.web.vo.Usuario;

/**
 * Backing Bean class to manage the users logged.
 * 
 * @author Baracho
 * 
 * @since July 05, 2018
 *
 * @version 1.0
 *
 */
@Named("contexttUsuariosBean")
@ApplicationScoped
public class ContextUsuariosBean implements Serializable {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -4630707726672659798L;
	/**
	 * Instance variables
	 */

	private List<Usuario> usuariosLogados;

	/**
	 * Default constructor
	 */
	public ContextUsuariosBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Access methods
	 */

	/**
	 * @return the usuariosLogados
	 */
	public List<Usuario> getUsuariosLogados() {
		return usuariosLogados;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuariosLogados(Usuario usuario) {

		if (this.usuariosLogados == null) {

			this.usuariosLogados = new ArrayList<Usuario>();
		}

		this.usuariosLogados.add(usuario);
	}

	/**
	 * Class operations
	 */

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void removerUsuario(Usuario usuario) {

		if (this.usuariosLogados != null) {

			this.usuariosLogados.remove(usuario);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuariosLogados == null) ? 0 : usuariosLogados.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ContextUsuariosBean)) {
			return false;
		}
		ContextUsuariosBean other = (ContextUsuariosBean) obj;
		if (usuariosLogados == null) {
			if (other.usuariosLogados != null) {
				return false;
			}
		} else if (!usuariosLogados.equals(other.usuariosLogados)) {
			return false;
		}
		return true;
	}
}
