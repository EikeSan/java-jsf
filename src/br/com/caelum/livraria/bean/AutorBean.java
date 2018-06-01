package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;


@ManagedBean
@ApplicationScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		this.autor = new Autor();
		
		return("livro?faces-redirect=true");
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public void remover(Autor autor) {

		new DAO<Autor>(Autor.class).remove(autor);
		this.autor = new Autor();
	}

	public String alterar(Autor autor) {
		this.autor = autor;
		System.out.println(this.autor.getNome());
		return ("carregarAutor?faces-redirect=true");
	}
	
	public String update() {
		
		new DAO<Autor>(Autor.class).atualiza(this.autor);

		this.autor= new Autor();
		
		return("autor?faces-redirect=true");
	}
}