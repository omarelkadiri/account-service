package ccn.elkadiri.account_service.DTO;

import ccn.elkadiri.account_service.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountResponseDTO {
    private String id;
    private String currency;
    private Date createdAt;
    private Double balance;
    private AccountType type;
}
