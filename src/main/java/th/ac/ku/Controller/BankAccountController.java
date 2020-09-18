package th.ac.ku.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.service.BankAccountService;
import th.ac.ku.Model.BankAccount;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {
    BankAccountService bankAccountService ;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String GetBankaccountPage(Model model){
        model.addAttribute("AllAccount",bankAccountService.getBankAccounts());
        return "bankaccount";

    }
    @PostMapping
    public String RegisterBankaccount(@ModelAttribute BankAccount bankAccount, Model model){
        bankAccountService.CreateAccount(bankAccount);
        model.addAttribute("Allaccount",bankAccountService.getBankAccounts());
        return "redirect:bankaccount";
    }

}
