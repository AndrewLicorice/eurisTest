package com.example.euris.dao;


import java.util.List;
import com.example.euris.model.Article;

public interface IDao {

	
	 List <Article> articles ();
	 
	 Article article (int code);
	 
	 void add (Article a);
	 
	 void delete (int code);
	 
	 void update (Article a);
	
	
	

	
}
