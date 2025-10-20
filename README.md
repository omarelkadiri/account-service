# Présentation du travail — Microservice de gestion de comptes bancaires

Ce projet est un microservice Spring Boot qui implémente la gestion de comptes bancaires. Il a été réalisé dans le cadre d'un travail pratique inspiré de la vidéo : https://www.youtube.com/watch?v=2-qIoZcvhAw

Objectifs et périmètre :
- Implémenter une application Spring Boot utilisant : Spring Web, Spring Data JPA, H2 (base en mémoire) et Lombok.
- Créer l'entité JPA `Compte`, le repository Spring Data, la couche service (métier), les DTOs/mappers, et le contrôleur REST pour exposer des opérations CRUD.
- Tester la couche DAO et les endpoints REST (tests unitaires et d'intégration).
- Documenter les API avec Swagger/OpenAPI.
- Exposer une API REST via Spring Data REST et utiliser des projections.
- Fournir des captures d'écran démontrant le fonctionnement (Postman, Swagger, H2, résultats de tests).

Le projet a pour but à la fois pédagogique (apprendre l'architecture microservice REST avec Spring) et fonctionnel (offrir une API simple pour la gestion de comptes bancaires).

---

## Captures d'écran

Cette section met en avant les principales fonctionnalités et résultats du projet `account-service` à travers des captures d'écran réalisées lors du développement et des tests. Ces images sont stockées dans le répertoire `src/screens/` et illustrent les endpoints de l'API, l'utilisation des DTOs et les fonctionnalités de Spring Data REST.

- **[BanlaceGreaterThan_Request.png](src/screens/BanlaceGreaterThan_Request.png)**  
  Affiche une requête GET vers `/bankAccounts/search/byBalanceGreaterThan` avec Postman, filtrant les comptes bancaires dont le solde est supérieur à une valeur spécifiée.

- **[Get-All_bank_Accounts.png](src/screens/Get-All_bank_Accounts.png)**  
  Montre la réponse d'une requête GET vers `/bankAccounts`, listant tous les comptes bancaires au format JSON en utilisant `BankAccountResponseDTO`.

- **[Get_single_account_DOT_versi...](src/screens/Get_single_account_DOT_versi...)**  
  Illustre une requête GET vers `/bankAccounts/{id}` avec une version DTO, mettant en évidence la récupération des détails d'un seul compte bancaire.

- **[Get_single_bank_account.png](src/screens/Get_single_bank_account.png)**  
  Présente la réponse JSON pour un compte bancaire récupéré par ID, montrant le mappage de `BankAccountResponseDTO`.

- **[Postman_Put_Method.png](src/screens/Postman_Put_Method.png)**  
  Affiche une requête PUT vers `/bankAccounts/{id}` dans Postman, démontrant la mise à jour d'un compte bancaire avec des données partielles de `BankAccountRequestDTO`.

- **[SearchByType_SpringDataAPI.png](src/screens/SearchByType_SpringDataAPI.png)**  
  Montre une requête GET vers `/bankAccounts/search/byType` utilisant Spring Data REST, filtrant les comptes par type (ex. "SAVINGS").

- **[h2-db-interface.png](src/screens/h2-db-interface.png)**  
  Présente l'interface de la base de données H2, illustrant la persistance des données des comptes bancaires dans la base embarquée.

- **[main](src/screens/main)**  
