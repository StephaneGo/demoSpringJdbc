package fr.eni.demoSpringJDBC.dal;

import fr.eni.demoSpringJDBC.bo.Pizza;
import fr.eni.demoSpringJDBC.exception.PizzaNotFound;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
        //String sql = "select id, nom, prix from pizza  where id = ?";

        Pizza pizza = null;

        try {
            pizza = jdbcTemplate.queryForObject(sql, new PizzaRowMapper(), id);

        }catch(EmptyResultDataAccessException e){
            throw new PizzaNotFound();
        }


        return pizza;
    }

    @Override
    public Pizza savePizza(Pizza pizza) {
        String sql = "insert into pizza (id, nom, prix) values (?, ?, ?)";

        PreparedStatementSetter pss = new PreparedStatementSetter() {
            @Override public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, pizza.getId());
                ps.setString(2, pizza.getNom());
                ps.setFloat(3, pizza.getPrix());
            } };
         jdbcTemplate.update(sql, pss );

        return pizza;
    }

    public void deletePizza(int id) {
        String sql = "delete from pizza where id = ?";
        jdbcTemplate.update(sql, id);
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
