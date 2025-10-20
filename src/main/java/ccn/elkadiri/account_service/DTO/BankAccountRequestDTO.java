package ccn.elkadiri.account_service.DTO;

import ccn.elkadiri.account_service.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccountRequestDTO {
    private String currency;
    private Double balance;
    private AccountType type;
}
