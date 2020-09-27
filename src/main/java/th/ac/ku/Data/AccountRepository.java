package th.ac.ku.Data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.ac.ku.Model.BankAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountRepository {
    private JdbcTemplate jdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BankAccount> findAll() {
        String query = "SELECT * FROM customer";
        List<BankAccount> customers =
                jdbcTemplate.query(query, new CustomerMapper());
        return customers;

    }

    public BankAccount findById(int id) {
        String query = "SELECT * FROM customer WHERE id = " + id;
        BankAccount bankAccount =
                jdbcTemplate.queryForObject(query, new CustomerMapper());
        return bankAccount;
    }

    public void save(BankAccount bankAccount) {
        String query = "INSERT INTO customer (id,customerid,type,balance) VALUES (?,?,?,?);";
        Object[] data = new Object[]
                { bankAccount.getId(), bankAccount.getCustomerId(), bankAccount.getType(),bankAccount.getBalance() };
        jdbcTemplate.update(query, data);
    }

    public void deleteById(int id) {
        String query = "DELETE FROM customer WHERE id = " + id;
        jdbcTemplate.update(query);
    }

    class CustomerMapper implements RowMapper<BankAccount> {

        @Override
        public BankAccount mapRow(ResultSet resultSet, int i)
                throws SQLException {

            int id = resultSet.getInt("id");
            int customerid = resultSet.getInt("customerid");
            String type = resultSet.getString("type");
            float balance = resultSet.getFloat("balance");

            BankAccount customer = new BankAccount(id, customerid,type, balance);
            return customer;
        }


    }

}


