package ccn.elkadiri.account_service.mappers;

import ccn.elkadiri.account_service.DTO.BankAccountRequestDTO;
import ccn.elkadiri.account_service.DTO.BankAccountResponseDTO;
import ccn.elkadiri.account_service.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Component
public class AccountMapper {
    public BankAccount toBankAccount(BankAccountResponseDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO, bankAccount);
        return bankAccount;
    }

    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount) {
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }

    public BankAccount toBankAccount(BankAccountRequestDTO bankAccountDTO) {
        return BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
    }

    public void updateBankAccountFromDTO(BankAccount bankAccount, BankAccountRequestDTO bankAccountDTO) {
        if (bankAccountDTO.getBalance() != null) bankAccount.setBalance(bankAccountDTO.getBalance());
        if (bankAccountDTO.getType() != null) bankAccount.setType(bankAccountDTO.getType());
        if (bankAccountDTO.getCurrency() != null) bankAccount.setCurrency(bankAccountDTO.getCurrency());
        // id et createdAt restent inchangés
    }
}
