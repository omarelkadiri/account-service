package ccn.elkadiri.account_service.repositories;

import ccn.elkadiri.account_service.entities.BankAccount;
import ccn.elkadiri.account_service.enums.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    @RestResource(path="/byType")
    List<BankAccount> findByType(@Param("t") AccountType type);

    // 1. Recherche par devise (currency)
    @RestResource(path = "/byCurrency")
    List<BankAccount> findByCurrency(@Param("c") String currency);

    // 2. Recherche par solde supérieur à une valeur
    @RestResource(path = "/byBalanceGreaterThan")
    List<BankAccount> findByBalanceGreaterThan(@Param("b") Double balance);

    // 3. Recherche par type et devise
    @RestResource(path = "/byTypeAndCurrency")
    List<BankAccount> findByTypeAndCurrency(@Param("t") AccountType type, @Param("c") String currency);

    // 4. Recherche par plage de dates de création
    @RestResource(path = "/byCreatedAtBetween")
    List<BankAccount> findByCreatedAtBetween(@Param("start") Date startDate, @Param("end") Date endDate);

    // 5. Recherche par ID exact
    @RestResource(path = "/byId")
    Optional<BankAccount> findById(@Param("id") String id);

    // 6. Recherche par solde inférieur ou égal
    @RestResource(path = "/byBalanceLessThanEqual")
    List<BankAccount> findByBalanceLessThanEqual(@Param("b") Double balance);

    // 7. Comptage des comptes par type
    @RestResource(path = "/countByType")
    Long countByType(@Param("t") AccountType type);

    // 8. Vérification de l'existence d'un compte par ID
    @RestResource(path = "/existsById")
    boolean existsById(@Param("id") String id);

    // 9. Recherche des comptes avec solde nul
    @RestResource(path = "/byBalanceIsNull")
    List<BankAccount> findByBalanceIsNull();

    // 10. Recherche par type avec pagination
    @RestResource(path = "/byTypePaged")
    Page<BankAccount> findByType(@Param("t") AccountType type, Pageable pageable);
}
