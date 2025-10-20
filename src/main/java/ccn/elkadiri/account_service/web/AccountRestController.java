package ccn.elkadiri.account_service.web;

import ccn.elkadiri.account_service.DTO.BankAccountRequestDTO;
import ccn.elkadiri.account_service.DTO.BankAccountResponseDTO;
import ccn.elkadiri.account_service.entities.BankAccount;
import ccn.elkadiri.account_service.mappers.AccountMapper;
import ccn.elkadiri.account_service.repositories.BankAccountRepository;
import ccn.elkadiri.account_service.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/MyApi/")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccountResponseDTO> findAll() {
        return bankAccountRepository.findAll().stream()
                .map(accountMapper::fromBankAccount)
                .collect(Collectors.toList());
    }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO bankAccount(@PathVariable String id) {
        return accountMapper.fromBankAccount(
                bankAccountRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException(String.format("Bank account with id %s not found", id)))
        );
    }

    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountResponseDTO update(@PathVariable String id, @RequestBody BankAccountRequestDTO bankAccountDTO) {
        return accountService.updateAccount(id, bankAccountDTO);
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id) {
        accountService.deleteAccount(id);
    }
}
