package com.example.euris.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.euris.model.Article;
import com.example.euris.util.BasicDao;

@Repository
public class Dao extends BasicDao implements IDao {

	public Dao(
			@Value("${db.address}") String dbAddress,
			@Value("${db.user}") String user,
			@Value("${db.psw}") String password) {
	        super(dbAddress, user, password);	
	}

	
	// HELPERS 
	
	// Crea un nuovo articolo. Trasferisce, attraverso fromMap (BasicDao), gli attributi
	// dalla mappa all'oggetto
	private Article articleFromMap (Map <String, String> map) {
		Article a = new Article();
		a.fromMap(map);
		return a;
		
	}
	
	
	
	
	// METHODS
	
	// LIST
	// interroga il database e mi ritorna la lista degli articoli
	@Override
	public List<Article> articles() {
		List<Article> res = new ArrayList <>();
		
		List<Map<String, String>> maps = getAll("SELECT * FROM articles");
		
		for (Map<String, String>map : maps) {
			Article a = articleFromMap(map);
			res.add(a);
			
		}
		
		return res;
	}

	
	
	
	
	
	// ARTICLE BY CODE
	// interroga il database e mi ritorna un singolo articolo in base al code (primary key)
	// che gli ho passato come parametro
	@Override
	public Article article(int code) {
		Article res = null;
		
		Map <String, String> map = getOne("SELECT * FROM articles WHERE code = ?", code);
		
		if(map != null) {
			res = articleFromMap(map);	
		}
		
		return res;
	}

	
	
	
	
	
	// ADD ARTICLE
	// aggiunge un oggetto article nel database passandogli l'oggetto con gli attributi name e cost
	// il code è auto_increment perciò non comunicarlo al database
	@Override
	public void add(Article a) {
		execute ("INSERT INTO articles (name, cost) VALUES (?, ?)", a.getName(), a.getCost());
		
	}

	
	
     // DELETE ARTICLE
	// elimino l'articolo nel database passandogli come parametro il code dell'articolo
	@Override
	public void delete(int code) {
		execute("DELETE FROM articles WHERE code = ?", code);
		
	}

	
	// UPDATE ARTICLE
	// modifico l'articolo nel database passandogli come parametro l'oggetto "modificato"
	@Override
	public void update(Article a) {
		execute ("UPDATE articles SET name = ?, cost = ? WHERE code = ?", 
				a.getName(), a.getCost(), a.getCode());
		
	}

}
