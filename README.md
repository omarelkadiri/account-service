# Service de comptes bancaires (Account Service)

Ce dépôt contient un microservice Spring Boot pour gérer des comptes bancaires — implémentation guidée selon l'exercice (vidéo) : https://www.youtube.com/watch?v=2-qIoZcvhAw.

Ce README (en français) couvre les étapes demandées, la manière de lancer le projet, les endpoints exposés, des exemples de requêtes, l'utilisation de Swagger et Spring Data REST, ainsi que l'emplacement des captures d'écran (dossier screens).

IMPORTANT : les captures d'écran sont attendues dans le dossier screens/ du dépôt. Si les noms de fichiers diffèrent, remplacez-les par les noms réels.

---

## Table des matières

- Présentation
- Pré-requis
- Étapes réalisées
  1. Création du projet Spring Boot
  2. Entité JPA Compte
  3. Interface CompteRepository
  4. Tests de la couche DAO
  5. Web service REST pour gérer les comptes
  6. Tests avec Postman / curl
  7. Documentation Swagger
  8. Spring Data REST et projections
  9. DTOs et Mappers
  10. Couche Service (métier)
- Lancer le projet
- Endpoints et exemples
- Tests automatisés
- Captures d'écran
- Notes et améliorations possibles

---

## Présentation

Microservice de gestion de comptes bancaires (CRUD). Le projet utilise : Spring Boot, Spring Web, Spring Data JPA, H2 (base en mémoire), Lombok.

L'objectif de cet exercice : implémenter l'entité Compte, le repository, la couche service, le contrôleur REST, exposer une API via Spring Data REST et documenter les API avec Swagger.

## Pré-requis

- Java 11+ (ou version configurée dans le projet)
- Maven 3+
- Postman ou curl pour tester l'API
- (Optionnel) IDE : IntelliJ / VSCode

## Étapes réalisées (résumé)

1) Créer un projet Spring Boot avec les dépendances : Spring Web, Spring Data JPA, H2 Database, Lombok.

2) Créer l'entité JPA `Compte` (exemple) :

```java
@Entity
@Data // Lombok
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proprietaire;
    private String type; // e.g. COURANT, EPARGNE
    private BigDecimal solde;
    private boolean active;
}
```

3) Interface `CompteRepository` (Spring Data JPA) :

```java
public interface CompteRepository extends JpaRepository<Compte, Long> {
    List<Compte> findByProprietaireContainsIgnoreCase(String proprietaire);
}
```

4) Tester la couche DAO : création d'un test JUnit (SpringBootTest) qui charge le contexte, insère des comptes et vérifie les opérations de base. Exemple : `CompteRepositoryTest`.

5) Créer le Web service REST (Controller) :

```java
@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
public class CompteController {
    private final CompteService compteService;

    @GetMapping
    public List<CompteDto> all() {
        return compteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteDto> getOne(@PathVariable Long id) {
        return ResponseEntity.of(compteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CompteDto> create(@RequestBody CompteDto dto) {
        CompteDto created = compteService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompteDto> update(@PathVariable Long id, @RequestBody CompteDto dto) {
        return ResponseEntity.of(compteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

6) Tester le micro-service avec Postman / curl (exemples ci-dessous).

7) Générer et tester la documentation Swagger (springdoc-openapi ou springfox) : accéder à `http://localhost:8080/swagger-ui.html` ou `http://localhost:8080/swagger-ui/index.html` selon la configuration.

8) Exposer une API RESTful via Spring Data REST : activer `spring-data-rest` ou annoter les repositories ; utiliser projections pour limiter/définir les vues des entités exposées (interfaces `@Projection`). Ex. projection `CompteProjection` retourne id, proprietaire, solde.

9) Créer DTOs et Mappers : utiliser MapStruct ou faire manuellement. Exemple DTO `CompteDto` : id, proprietaire, type, solde, active. Mapper `CompteMapper` pour convertir entre `Compte` et `CompteDto`.

10) Couche Service (métier) : `CompteService` avec méthodes `getAll`, `getById`, `create`, `update`, `delete`. Gère la logique métier et les conversions DTO <-> Entity.

---

## Lancer le projet

1) Cloner le dépôt :

```bash
git clone https://github.com/omarelkadiri/account-service.git
cd account-service
```

2) Compiler et lancer avec Maven :

```bash
mvn clean package
mvn spring-boot:run
```

ou exécuter le jar :

```bash
java -jar target/account-service-0.0.1-SNAPSHOT.jar
```

L'application démarre par défaut sur le port 8080.

Base H2 accessible (si configurée) : `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb` par défaut).

---

## Endpoints (exemples)

API REST via controller (exemples) :

- GET /api/comptes  -> récupérer tous les comptes
- GET /api/comptes/{id} -> récupérer un compte par id
- POST /api/comptes -> créer un compte (JSON body)
- PUT /api/comptes/{id} -> mettre à jour un compte
- DELETE /api/comptes/{id} -> supprimer un compte

Exemples curl :

Créer un compte :
```bash
curl -X POST http://localhost:8080/api/comptes -H "Content-Type: application/json" -d '{"proprietaire":"Alice","type":"EPARGNE","solde":1000.0,"active":true}'
```

Récupérer tous les comptes :
```bash
curl http://localhost:8080/api/comptes
```

Récupérer un compte :
```bash
curl http://localhost:8080/api/comptes/1
```

Mettre à jour :
```bash
curl -X PUT http://localhost:8080/api/comptes/1 -H "Content-Type: application/json" -d '{"proprietaire":"Alice B","type":"EPARGNE","solde":1200.0,"active":true}'
```

Supprimer :
```bash
curl -X DELETE http://localhost:8080/api/comptes/1
```

---

## Swagger / OpenAPI

- Si le projet inclut `springdoc-openapi-ui`, l'UI Swagger est disponible à :
  - `http://localhost:8080/swagger-ui.html` ou `http://localhost:8080/swagger-ui/index.html`
  - Le fichier OpenAPI JSON est souvent disponible à : `http://localhost:8080/v3/api-docs`

Ajout rapide (pom.xml) si absent :
```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-ui</artifactId>
  <version>1.8.0</version>
</dependency>
```

---

## Spring Data REST et projections

- Pour exposer directement les repositories comme API REST, ajouter `spring-boot-starter-data-rest` et annoter ou configurer vos projections.

Exemple projection :
```java
@Projection(name = "compteSimple", types = Compte.class)
public interface CompteSimpleProjection {
    Long getId();
    String getProprietaire();
    BigDecimal getSolde();
}
```

Appel : `GET /comptes?projection=compteSimple` (chemin dépend de Spring Data REST).

---

## DTOs et Mappers

Exemple DTO :
```java
@Data
public class CompteDto {
    private Long id;
    private String proprietaire;
    private String type;
    private BigDecimal solde;
    private boolean active;
}
```

Mapper basique (manuellement) :
```java
public class CompteMapper {
    public static CompteDto toDto(Compte c) {
        CompteDto d = new CompteDto();
        d.setId(c.getId());
        d.setProprietaire(c.getProprietaire());
        d.setType(c.getType());
        d.setSolde(c.getSolde());
        d.setActive(c.isActive());
        return d;
    }
    public static Compte toEntity(CompteDto d) {
        Compte c = new Compte();
        c.setId(d.getId());
        c.setProprietaire(d.getProprietaire());
        c.setType(d.getType());
        c.setSolde(d.getSolde());
        c.setActive(d.isActive());
        return c;
    }
}
```

MapStruct peut automatiser cette conversion si vous préférez.

---

## Couche Service (exemple)

```java
@Service
@RequiredArgsConstructor
public class CompteService {
    private final CompteRepository repo;

    public List<CompteDto> getAll() {
        return repo.findAll().stream().map(CompteMapper::toDto).collect(Collectors.toList());
    }

    public Optional<CompteDto> getById(Long id) {
        return repo.findById(id).map(CompteMapper::toDto);
    }

    public CompteDto create(CompteDto dto) {
        Compte c = CompteMapper.toEntity(dto);
        c = repo.save(c);
        return CompteMapper.toDto(c);
    }

    public Optional<CompteDto> update(Long id, CompteDto dto) {
        return repo.findById(id).map(existing -> {
            existing.setProprietaire(dto.getProprietaire());
            existing.setType(dto.getType());
            existing.setSolde(dto.getSolde());
            existing.setActive(dto.isActive());
            return CompteMapper.toDto(repo.save(existing));
        });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
```

---

## Tests (DAO et intégration)

- Créer des tests unitaires pour `CompteRepository` en utilisant @DataJpaTest et H2 en mémoire.
- Ajouter des tests d'intégration pour les endpoints REST (`@SpringBootTest` avec TestRestTemplate ou MockMvc).

Exemple de test DAO (DataJpaTest) :
```java
@DataJpaTest
public class CompteRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private CompteRepository repo;

    @Test
    public void testSaveAndFind() {
        Compte c = new Compte();
        c.setProprietaire("Bob");
        c.setType("COURANT");
        c.setSolde(new BigDecimal("500"));
        em.persist(c);
        em.flush();

        List<Compte> found = repo.findByProprietaireContainsIgnoreCase("bob");
        assertFalse(found.isEmpty());
    }
}
```

---

## Captures d'écran

Les captures d'écran démontrant le fonctionnement (Postman, Swagger, H2, résultats des tests) se trouvent dans le dossier `screens/`. Les images sont référencées ci-dessous — si un nom ne correspond pas, remplacez par le nom exact présent dans le dossier `screens/`.

- Liste des comptes (Postman) : ![Liste comptes](screens/list-comptes.png)
- Création d'un compte (Postman) : ![Création compte](screens/create-compte.png)
- Documentation Swagger : ![Swagger](screens/swagger.png)
- Spring Data REST / Projections : ![Spring Data REST](screens/spring-data-rest.png)
- Tests unitaires / DAO : ![Tests](screens/tests.png)

(Assurez-vous que les images sont présentes et que leurs noms correspondent. Sinon, modifiez les chemins en conséquence.)

---

## Améliorations possibles

- Ajouter gestion des exceptions centralisée (`@ControllerAdvice`) pour renvoyer des erreurs structurées.
- Ajouter sécurité (Spring Security) et authentification JWT.
- Ajouter pagination et tri pour la récupération des comptes.
- Ajouter opérations métiers (virement entre comptes, historique des transactions).

---

## Contact

Pour toute question ou modification de ce README, ouvrir une issue ou me contacter (omarelkadiri sur GitHub).
