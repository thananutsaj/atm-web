package th.ac.ku.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import th.ac.ku.Data.AccountRepository;
import th.ac.ku.Model.BankAccount;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {
    private AccountRepository repository;

    public BankAccountService(AccountRepository repository) {
        this.repository = repository;
    }


    public void CreateAccount(BankAccount bankAccount){
        repository.save(bankAccount);
    }
    public BankAccount findCustomer(int id) {
        try {
            return repository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<BankAccount> getBankAccounts() {
        return repository.findAll();
    }
}
