package fr.eni.demoSpringJDBC.dal;

import fr.eni.demoSpringJDBC.bo.Pizza;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PizzaRepositoryImplTest {
    @Autowired
    private PizzaRepository pizzaRepository;



    @Test
    @DisplayName("test ajout pizza cas droit")
    public void testAjoutPizzaCasDroit() {
        //AAA
        //Arrange : Préparation du test
        Pizza pizza = new Pizza(5, "reine2", 12.0f);

        //Act : Appel de la méthode à tester
        Pizza newPizza = pizzaRepository.savePizza(pizza);

        //Assert : Vérification du résultat du Act
        assertNotNull(pizza);
        Pizza piz = pizzaRepository.findPizzaById(pizza.getId());
        assertEquals(pizza, piz);

    }


    @Test
    @DisplayName("test findAllPizzas cas plusieurs pizzas existent") //Optionnel
    public void testFindAllPizzasCasPlusieursPizzas() {
        //AAA
        //Arrange : Préparation du test

        //Act : Appel de la méthode à tester
        List<Pizza> pizzas = pizzaRepository.findAllPizzas();

        //Assert : Vérification du résultat du Act
        assertNotNull(pizzas);
        assertEquals(3, pizzas.size());
    }

   // @Test
    //@DisplayName("test findAllPizzas cas plusieurs aucune pizza")
    public void testFindAllPizzasCasAucunePizza() {
        //AAA
        //Arrange : Préparation du test

        //Act : Appel de la méthode à tester
        List<Pizza> pizzas = pizzaRepository.findAllPizzas();

        //Assert : Vérification du résultat du Act
        assertNotNull(pizzas);
        assertEquals(0, pizzas.size());

    }

    @Test
    @DisplayName("test findPizzaById cas l'id existe")
    public void testFindPizzaCasIdExiste() {
        //AAA
        //Arrange : Préparation du test
        int id = 2;

        //Act : Appel de la méthode à tester
        Pizza pizza = pizzaRepository.findPizzaById(id);

        //Assert : Vérification du résultat du Act
        assertNotNull(pizza);
        assertEquals(id,  pizza.getId());
        assertEquals("DELLA NONA", pizza.getNom());

    }

}
