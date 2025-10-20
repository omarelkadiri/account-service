package ccn.elkadiri.account_service.entities;

import ccn.elkadiri.account_service.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data @NoArgsConstructor  @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private String currency;
    // @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    //@Enumerated(EnumType.STRING)
    //private AccountStatus status;
    //private Long customerId;
}
