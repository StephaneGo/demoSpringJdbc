package fr.eni.demoSpringJDBC.dal;

import fr.eni.demoSpringJDBC.bo.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository {

    List<Pizza> findAllPizzas();

    Pizza findPizzaById(int id);

    //Ajouter une pizza
    Pizza savePizza(Pizza pizza);

    public void deletePizza(int id);
}
