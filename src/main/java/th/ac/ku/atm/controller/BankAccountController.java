package th.ac.ku.atm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {
    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.createBankAccount(bankAccount);
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model) {
        BankAccount bankAccount = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-edit";
    }

//    @PostMapping("/edit/{id}")
//    public String editBankAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {
//        bankAccountService.editBankAccount(bankAccount);
//        model.addAttribute("allBankAccounts", bankAccountService.getBankAccounts());
//        return "redirect:/bankaccount";
//    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model, double amount, String btn) {

        BankAccount record = bankAccountService.getBankAccount(bankAccount.getId());
        if (btn.equals("withdraw"))
            record.setBalance(record.getBalance()-amount);
        else if (btn.equals("deposit"))
            record.setBalance(record.getBalance()+amount);
        bankAccountService.editBankAccount(record);

        model.addAttribute("allBankAccounts",bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id, @ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.deleteBankAccount(bankAccount);
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }


}
