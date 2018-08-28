package form.login.cadastro.web.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to represent the Usuario entity.
 * 
 * @author Baracho
 * 
 * @since June 09, 2018
 * 
 * @version 3.0
 * 
 */

public class Usuario implements Serializable, Cloneable {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = 7188322045094457251L;

	/**
	 * Constants variables
	 */

	private final Character MALE = new Character('M');

	private final Character FEMALE = new Character('F');

	/**
	 * Instance variables
	 */

	private Integer id;

	private String nome;

	private Date dataNascimento;

	private Character sexo;

	private String email;

	private Boolean status;

	private String login;

	private String senha;

	/**
	 * Default constructor
	 */

	public Usuario() {

	}

	/**
	 * Parameterized constructor
	 * 
	 * @param id
	 *            the id to set
	 * @param nome
	 *            the nome to set
	 * @param dataNascimento
	 *            the dataNascimento to set
	 * @param sexo
	 *            the sexo to set
	 * @param email
	 *            the email to set
	 * @param status
	 *            the status to set
	 * @param login
	 *            the login to set
	 * @param senha
	 *            the senha to set
	 */
	public Usuario(Integer id, String nome, Date dataNascimento, Character sexo, String email, Boolean status,
			String login, String senha) {

		this.setId(id);

		this.setNome(nome);

		this.setDataNascimento(dataNascimento);

		this.setSexo(sexo);

		this.setEmail(email);

		this.setStatus(status);

		this.setLogin(login);

		this.setSenha(senha);
	}

	/**
	 * Access to set
	 */

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	/**
	 * @return the dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento
	 *            the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @return the sexo
	 */
	public Character getSexo() {
		return sexo;
	}

	/**
	 * @param sexo
	 *            the sexo to set
	 */
	public void setSexo(Character sexo) {

		if (sexo != null) {

			sexo = Character.toUpperCase(sexo.charValue());

			if (sexo.equals(MALE) || sexo.equals(FEMALE)) {

				this.sexo = sexo;

			} else {

				this.sexo = null;

			}

		} else {

			this.sexo = null;
		}
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha
	 *            the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Class operations
	 */

	/**
	 * @return the age
	 */
	public int calculateAge() {

		// variables declaration

		int age = -1;

		Calendar currentDate = null;

		int monthCurrentDate = -1;

		int dayCurrentDate = -1;

		Calendar dob = null;

		int monthDOB = -1;

		int dayDOB = -1;

		// Data processing

		if (this.dataNascimento != null) {

			currentDate = Calendar.getInstance();

			dob = Calendar.getInstance();

			dob.setTime(this.dataNascimento);

			age = currentDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

			monthCurrentDate = currentDate.get(Calendar.MONTH);

			monthDOB = dob.get(Calendar.MONTH);

			if (monthDOB > monthCurrentDate) {

				age--;

			} else {

				if (monthDOB == monthCurrentDate) {

					dayCurrentDate = currentDate.get(Calendar.DAY_OF_MONTH);

					dayDOB = dob.get(Calendar.DAY_OF_MONTH);

					if (dayDOB > dayCurrentDate) {

						age--;
					}
				}
			}
		}

		// Information output

		return age;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		// Variables declaration

		String msg = null;

		// Data processing

		msg = String.format("\n ID: %s", this.id != null ? this.id.toString() : "");

		msg += String.format("\n Nome: %s", this.nome != null ? this.nome : "");

		msg += String.format("\n Data de Nascimento: %s",
				this.dataNascimento != null ? new SimpleDateFormat("dd/MM/yyyy").format(this.dataNascimento) : "");

		msg += String.format("\n Idade: %s", this.dataNascimento != null ? calculateAge() + " anos" : "");

		msg += String.format("\n Sexo: %s",
				this.sexo != null ? (this.sexo.equals(MALE) ? "Masculino" : "Feminino") : "");

		msg += String.format("\n E-maiL: %s", this.email != null ? this.email : "");

		msg += String.format("\n Status: %s",
				this.status != null ? (this.status.equals(true) ? "Ativo" : "Inativo") : "");

		msg += String.format("\n Login: %s", this.login != null ? this.login : "");

		msg += String.format("\n Senha: %s", this.senha != null ? this.senha : "");

		// Information output

		return msg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Usuario clone() throws CloneNotSupportedException {

		// Data processing

		// Shallow copy

		Usuario cloned = (Usuario) super.clone();

		// Deep cloning

		if (this.dataNascimento != null) {

			cloned.dataNascimento = (Date) this.dataNascimento.clone();
		}

		// Information output

		return cloned;
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
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (dataNascimento == null) {
			if (other.dataNascimento != null) {
				return false;
			}
		} else if (!dataNascimento.equals(other.dataNascimento)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (senha == null) {
			if (other.senha != null) {
				return false;
			}
		} else if (!senha.equals(other.senha)) {
			return false;
		}
		if (sexo == null) {
			if (other.sexo != null) {
				return false;
			}
		} else if (!sexo.equals(other.sexo)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}
}