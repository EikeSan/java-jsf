package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ApplicationScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;

	public Livro getLivro() {
		return livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	};

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);

	}

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public void gravar() {
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);

		this.livro = new Livro();
	}

	public void remover(Livro livro) {

		new DAO<Livro>(Livro.class).remove(livro);
		this.livro = new Livro();
	}

	public String alterar(Livro livro) {
		this.livro = livro;
		System.out.println(this.livro.getTitulo());
		return ("carregarLivro?faces-redirect=true");
	}
	
	public String update() {
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return("");
		}

		new DAO<Livro>(Livro.class).atualiza(this.livro);

		this.livro = new Livro();
		
		return("livro?faces-redirect=true");
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN deve começar com 1"));
		}

	}

	public String autorForm() {
		return ("autor?faces-redirect=true");

	}
	
	public String homePage() {
		this.livro = new Livro();
		return ("livro?faces-redirect=true");
	}

	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
}
