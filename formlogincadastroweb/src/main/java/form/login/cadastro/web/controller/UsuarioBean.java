package form.login.cadastro.web.controller;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import form.login.cadastro.web.model.UsuarioModel;
import form.login.cadastro.web.util.ContextUsuarioBean;
import form.login.cadastro.web.util.UsuarioModelException;
import form.login.cadastro.web.vo.Usuario;

/**
 * Backing Bean class to support the JSF pages to the Usuario entity.
 * 
 * @since June 10, 2018
 * 
 * @author Baracho
 * 
 * @version 2.0
 * 
 */

@Named("usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

	/**
	 * Generated serial version ID
	 */

	private static final long serialVersionUID = 4790044098281089192L;

	/**
	 * Instance variables
	 */

	private Usuario usuario;

	private List<Usuario> usuarios;

	private String confirmaSenha;

	private UsuarioModel usuarioModel;

	private String errorPageMessage;

	private String caption;

	private String message;

	private String operation;

	@Inject
	private ContextUsuarioBean contextUsuarioBean;

	/**
	 * Default constructor
	 */
	public UsuarioBean() {
		// TODO Auto-generated constructor stub

		this.usuario = new Usuario();

		this.usuarioModel = new UsuarioModel();
	}

	/**
	 * Access methods
	 */

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @return the confirmaSenha
	 */
	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	/**
	 * @param confirmaSenha
	 *            the confirmaSenha to set
	 */
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	/**
	 * @return the errorPage
	 */
	public String getErrorPageMessage() {
		return errorPageMessage;
	}

	/**
	 * @param errorPageMessage
	 *            the errorPage to set
	 */
	public void setErrorPageMessage(String errorPageMessage) {
		this.errorPageMessage = errorPageMessage;
	}

	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * Class operations
	 */
	
	/**
	 * @return the navigation rule
	 */
	public String confirmarDados() {

		FacesContext context = FacesContext.getCurrentInstance();

		Usuario user = null;

		if (this.usuario.getId() == null) {

			try {

				user = this.usuarioModel.validateLogin(usuario.getLogin());

				this.caption = "Cadastrar - entre com os dados";

			} catch (UsuarioModelException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

				user = null;
			}

		} else {

			try {

				user = this.usuarioModel.validateLogin(
						this.usuario.getLogin(), this.usuario.getId());

				this.caption = "Atualizar - entre com os dados";

			} catch (UsuarioModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (user != null) {

			context.addMessage(
					null, 
					new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"Login já cadastrado! Favor informar novo login.", 
					""));

			return null;
		}

		if (!this.usuario.getSenha().equals(this.confirmaSenha)) {

			context.addMessage(
					null, 
					new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Senha confirmada incorretamente! Digite novamente o campo.", 
					""));

			return null;
		}

		return "confirmarCadastro";
	}

	/**
	 * @return the navigation rule
	 */
	public String saveOrUpdate() {

		try {

			if (this.usuario.getId() == null) {

				this.caption = "Dados salvos com sucesso";

				this.operation = "save";

			} else {

				this.caption = "Dados atualizados com sucesso";

				this.operation = "update";
			}

			this.usuarioModel.saveOrUpdate(usuario);

		} catch (UsuarioModelException e) {
			// TODO Auto-generated catch block

			this.setErrorPageMessage(
					"UsuarioBean - saveOrUpdate(): " 
					+ "não foi possível salvar/atualizar os dados do usuário.");

			return "/pages/exceptions/errorpage";
		}

		try {

			this.usuarios = this.usuarioModel.list();

		} catch (UsuarioModelException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			this.setErrorPageMessage(
					"UsuarioBean - salveOrUpdadte(): " 
					+ "não foi possível consultar os dados do usuário.");

			return "/pages/exceptions/errorpage";
		}

		return "mostrarCadastro";
	}

	/**
	 * @return the navigation rule
	 */
	public String atualizar() {

		this.confirmaSenha = this.usuario.getSenha();

		this.caption = "Atualizar - entre com os dados";

		return "/pages/cadastro/cadastrarUsuario";
	}

	/**
	 * @return the navigation rule
	 */
	public String consultar() {

		try {

			this.usuarios = this.usuarioModel.list();

		} catch (UsuarioModelException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			this.setErrorPageMessage(
					"UsuarioBean - consultar(): " 
					+ "não foi possível consultar os dados do usuário.");

			return "/pages/exceptions/errorpage";
		}

		if (this.usuarios == null || this.usuarios.size() == 0) {

			this.message = "No momento não existe usuários cadastrados";
		}

		this.caption = "Consultar";

		return "/pages/consulta/consultarUsuarios";
	}

	/**
	 * @return the navigation rule
	 */
	public String excluir() {

		FacesContext context = FacesContext.getCurrentInstance();

		List<Usuario> usuariosLogados = null;

		Iterator<Usuario> iterator = null;

		Usuario user = null;

		Boolean flag = null;

		if (this.usuario.getLogin().equals(
				this.contextUsuarioBean.getUsuarioLogado().getLogin())
				&& this.usuario.getSenha().equals(
				this.contextUsuarioBean.getUsuarioLogado().getSenha())) {

			context.addMessage(null,
					new FacesMessage(
					FacesMessage.SEVERITY_ERROR, 
					"Usuário "
					+ this.contextUsuarioBean.getUsuarioLogado().getLogin() 
					+ " logado! Não pode ser excluído.",
					""));

			return null;
		}

		usuariosLogados = this.contextUsuarioBean.getUsuariosLogados();

		if (usuariosLogados != null) {

			iterator = usuariosLogados.iterator();

			while (iterator.hasNext()) {

				user = iterator.next();

				if (this.usuario.getLogin().equals(user.getLogin())
						&& this.usuario.getSenha().equals(user.getSenha())) {

					context.addMessage(
							null, 
							new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Usuário " 
							+ user.getLogin() 
							+ " logado em outra seção! Não pode ser excluído.", 
							""));

					return null;
				}
			}
		}

		try {

			flag = this.usuarioModel.delete(this.usuario);

		} catch (UsuarioModelException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			this.setErrorPageMessage(
					"UsuarioBean - excluir(): " 
					+ "não foi possível excluir o usuário.");

			return "/pages/exceptions/errorpage";
		}

		if (flag != null && flag.equals(true)) {

			try {

				this.usuarios = this.usuarioModel.list();

			} catch (UsuarioModelException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

				this.setErrorPageMessage(
						"UsuarioBean - excluir(): " 
						+ "não foi possível consultar os dados.");

				return "/pages/exceptions/errorpage\"";
			}
		}

		return null;
	}

	/**
	 * @return the navigation rule
	 */
	public String ativar() {

		if (this.usuario != null) {

			if (this.usuario.getStatus() != null) {

				if (this.usuario.getStatus().equals(true)) {

					this.usuario.setStatus(new Boolean(false));

				} else {

					this.usuario.setStatus(new Boolean(true));
				}

			} else {

				this.usuario.setStatus(new Boolean(true));
			}

			try {

				this.usuarioModel.saveOrUpdate(this.usuario);

			} catch (UsuarioModelException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

				this.setErrorPageMessage(
						"UsuarioBean - ativar(): " 
						+ "não foi possível atualizar os dados do usuário.");

				return "/pages/exceoptions/errorpage";
			}

			try {

				this.usuarios = this.usuarioModel.list();

			} catch (UsuarioModelException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();

				this.setErrorPageMessage(
						"UsuarioBean - ativar(): " 
						+ "não foi possível consultar os dados.");

				return "/pages/exceoptions/errorpage";
			}
		}

		return null;
	}

	/**
	 * @return the navigation rule
	 */
	public String pesquisar() {

		this.usuarios = null;

		this.caption = "Pesquisar";

		return "/pages/pesquisa/pesquisarUsuario";
	}

	/**
	 * @param nome
	 * 			the nome to set
	 * @return the navigation rule
	 */
	public String pesquisaUsuario(String nome) {

		try {

			this.usuarios = this.usuarioModel.search(nome);

		} catch (UsuarioModelException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			this.setErrorPageMessage(
					"UsuarioBean - pesquisarUsuario(): " 
					+ "não foi possível pesquisar os dados do usuário.");

			return "/pages/exceptions/errorpage";
		}

		if (this.usuarios == null || this.usuarios.size() == 0) {

			this.message = "Nenhuma ocorrência para " + nome.toUpperCase();
		}

		return "/pages/consulta/consultarUsuarios";
	}

	/**
	 * @return the navigation rule
	 */
	public String login() {

		FacesContext context = FacesContext.getCurrentInstance();

		try {

			usuarioModel.authenticateUsuario(this.usuario);

			if (this.usuario != null && this.usuario.getId() == null) {

				context.addMessage(
						null, 
						new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"Nome ou senha informado incorretamente! " 
						+ "Favor informar novamente os dados.", 
						""));

				return null;

			} else {

				this.contextUsuarioBean.setUsuarioLogado(this.usuario);
			}

		} catch (UsuarioModelException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

			this.usuario = new Usuario();

			this.confirmaSenha = null;

			this.errorPageMessage = 
					"Erro de autenticação: não foi possível realizar o login.";

			return "/pages/exceptions/errorpage";
		}

		return "/pages/principal/homepage";
	}

	/**
	 * @return the navigation rule
	 */
	public String logout() {

		this.contextUsuarioBean.removerUsuario(this.usuario);

		this.usuario = new Usuario();

		this.confirmaSenha = null;

		return "/pages/login";
	}

	/**
	 * @return the navigation rule
	 */
	public String novo() {

		this.usuario = new Usuario();

		this.confirmaSenha = null;

		return "/pages/login";
	}

	/**
	 * @return the navigation rule
	 */
	public String homepage() {
		return "/pages/principal/homepage";
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
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		if (!(obj instanceof UsuarioBean)) {
			return false;
		}
		UsuarioBean other = (UsuarioBean) obj;
		if (usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!usuario.equals(other.usuario)) {
			return false;
		}
		return true;
	}
}