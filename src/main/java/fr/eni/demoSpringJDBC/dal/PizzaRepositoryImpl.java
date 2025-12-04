package fr.eni.demoSpringJDBC.dal;

import fr.eni.demoSpringJDBC.bo.Pizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {

    private JdbcTemplate jdbcTemplate;

    //injection d'un jdbcTemplate
    public PizzaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pizza> findAllPizzas() {
        String sql = "select id, nom, prix from pizza";
        List<Pizza> pizzas = jdbcTemplate.query(sql, new PizzaRowMapper());

        return pizzas;
    }

    @Override
    public Pizza findPizzaById(int id) {
        String sql = "select id, nom, prix from pizza  where id = ?";
        Pizza pizza = jdbcTemplate.queryForObject(sql, new PizzaRowMapper(), id);
        return pizza;
    }

    class PizzaRowMapper implements RowMapper<Pizza> {

        @Override
        public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pizza  pizza = new Pizza();
            pizza.setId(rs.getInt("id"));
            pizza.setNom(rs.getString("nom"));
            pizza.setPrix(rs.getFloat("prix"));
            return pizza;
        }
    }
}
