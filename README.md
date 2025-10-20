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

Cette section met en avant les principales fonctionnalités et résultats du projet `account-service` à travers des captures d'écran réalisées lors du développement et des tests. Ces images illustrent les endpoints de l'API, l'utilisation des DTOs et les fonctionnalités de Spring Data REST.

![Requête Balance Greater Than](https://raw.githubusercontent.com/omarelkadiri/account-service/master/src/Screens/BanalaceGreaterThan_Request.png)  
*Affiche une requête GET vers `/bankAccounts/search/byBalanceGreaterThan` avec Postman, filtrant les comptes bancaires dont le solde est supérieur à une valeur spécifiée.*

![Liste de tous les comptes bancaires](https://raw.githubusercontent.com/omarelkadiri/account-service/master/src/Screens/Get-All_bank_Accounts.png)  
*Montre la réponse d'une requête GET vers `/bankAccounts`, listant tous les comptes bancaires au format JSON en utilisant `BankAccountResponseDTO`.*

![Récupération d'un compte unique (version DTO)](https://raw.githubusercontent.com/omarelkadiri/account-service/master/src/Screens/Get_single_account_DOT_version.png)  
*Illustre une requête GET vers `/bankAccounts/{id}` avec une version DTO, mettant en évidence la récupération des détails d'un seul compte bancaire.*

![Mise à jour d'un compte via PUT](https://raw.githubusercontent.com/omarelkadiri/account-service/master/src/Screens/Postman_Put_Method.png)  
*Affiche une requête PUT vers `/bankAccounts/{id}` dans Postman, démontrant la mise à jour d'un compte bancaire avec des données partielles de `BankAccountRequestDTO`.*

![Recherche par type avec Spring Data REST](https://raw.githubusercontent.com/omarelkadiri/account-service/master/src/Screens/SearchByType_SpringDataAPI.png)  
*Montre une requête GET vers `/bankAccounts/search/byType` utilisant Spring Data REST, filtrant les comptes par type (ex. "SAVINGS").*

![Interface de la base de données H2](https://raw.githubusercontent.com/omarelkadiri/account-service/master/src/Screens/h2-db-interface.png)  
*Présente l'interface de la base de données H2, illustrant la persistance des données des comptes bancaires dans la base embarquée.*

### Notes
- Certaines images (comme `Get_single_bank_account.png` et `main.png`) n'ont pas pu être localisées ou semblent absentes du dépôt. Si elles existent sous un nom différent, ajoutez-les manuellement.
- Copiez-collez cette section directement dans votre `README.md` à la fin du fichier ou dans la partie appropriée. GitHub rendra les images automatiquement visibles lors de l'affichage du README.
- Si le dossier est `Screens` (avec S majuscule) et non `screens`, assurez-vous que les chemins dans les liens correspondent exactement (comme indiqué ci-dessus). Testez en commitant et pushant les changements sur GitHub.

- **[h2-db-interface.png](src/screens/h2-db-interface.png)**  
  Présente l'interface de la base de données H2, illustrant la persistance des données des comptes bancaires dans la base embarquée.

- **[main](src/screens/main)**  
