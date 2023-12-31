package org.java.app.db.serv;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepo pizzaRepo;

	public Pizza save(Pizza pizza) {
		return pizzaRepo.save(pizza);
	}

	public List<Pizza> findAll() {
		return pizzaRepo.findAll();
	}

	public List<Pizza> findPizzeByNome(String nome) {
		return pizzaRepo.findByNomeContaining(nome);
	}

	public void deletePizza(Pizza pizza) {
		pizzaRepo.delete(pizza);
	}

	public Optional<Pizza> findById(int id) {
		return pizzaRepo.findById(id);
	}

}
