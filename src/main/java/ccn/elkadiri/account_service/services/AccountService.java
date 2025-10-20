package ccn.elkadiri.account_service.services;

import ccn.elkadiri.account_service.DTO.BankAccountRequestDTO;
import ccn.elkadiri.account_service.DTO.BankAccountResponseDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccount);
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) ;
    public void deleteAccount(String id);
}
