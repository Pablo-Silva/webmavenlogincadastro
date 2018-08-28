package form.login.cadastro.web.util;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import form.login.cadastro.web.model.UsuarioModel;
import form.login.cadastro.web.vo.Usuario;

/**
 * Backing Bean class to manage the user logged.
 * 
 * @author Baracho
 * 
 * @since junho 19, 2018
 *
 * @version 1.0
 *
 */
@Named("contextUsuarioBean")
@SessionScoped
public class ContextUsuarioBean implements Serializable {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -5418433024799681220L;

	/**
	 * Instance variables
	 */

	private Usuario usuarioLogado;

	@Inject
	private ContextUsuariosBean contexttUsuariosBean;

	/**
	 * Default constructor
	 */
	public ContextUsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Access methods
	 */

	/**
	 * @return the usuarioLogado
	 */
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado
	 *            the usuarioLogado to set
	 */
	public void setUsuarioLogado(Usuario usuario) {

		if (usuario != null && !usuario.getLogin().equals("")) {

			UsuarioModel usuarioModel = new UsuarioModel();

			try {

				usuarioModel.authenticateUsuario(usuario);

				if (usuario != null && usuario.getId() != null) {

					this.contexttUsuariosBean.setUsuariosLogados(usuario);
				}

			} catch (UsuarioModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.usuarioLogado = usuario;
	}

	/**
	 * Class operations
	 */

	/**
	 * @return the usuariosLogados
	 */
	public List<Usuario> getUsuariosLogados() {
		return this.contexttUsuariosBean.getUsuariosLogados();
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void removerUsuario(Usuario usuario) {

		this.contexttUsuariosBean.removerUsuario(usuario);
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
		result = prime * result + ((usuarioLogado == null) ? 0 : usuarioLogado.hashCode());
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
		if (!(obj instanceof ContextUsuarioBean)) {
			return false;
		}
		ContextUsuarioBean other = (ContextUsuarioBean) obj;
		if (usuarioLogado == null) {
			if (other.usuarioLogado != null) {
				return false;
			}
		} else if (!usuarioLogado.equals(other.usuarioLogado)) {
			return false;
		}
		return true;
	}
}
