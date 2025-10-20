package ccn.elkadiri.account_service.services;

import ccn.elkadiri.account_service.DTO.BankAccountRequestDTO;
import ccn.elkadiri.account_service.DTO.BankAccountResponseDTO;
import ccn.elkadiri.account_service.entities.BankAccount;
import ccn.elkadiri.account_service.mappers.AccountMapper;
import ccn.elkadiri.account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bankAccountRepository;
    private AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
        public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

            BankAccount bankAccount = accountMapper.toBankAccount(bankAccountDTO);
            BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
            BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);

            return bankAccountResponseDTO;
        }

    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount existingBankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Bank account with id %s not found", id)));
        accountMapper.updateBankAccountFromDTO(existingBankAccount, bankAccountDTO);
        BankAccount updatedBankAccount = bankAccountRepository.save(existingBankAccount);
        return accountMapper.fromBankAccount(updatedBankAccount);
    }

    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}

