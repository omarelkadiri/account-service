package ccn.elkadiri.account_service.entities;

import ccn.elkadiri.account_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(types = BankAccount.class, name = "p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();
    public Double getBalance();
}