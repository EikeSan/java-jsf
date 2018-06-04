package br.com.caelum.livraria.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@ManagedBean
@ApplicationScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuarLogin() {

		boolean isExiste = new UsuarioDao().existe(this.usuario);

		if (isExiste) {
			return "livro?faces-redirect=true";
		}
		return null;

	}
}