# Cytrus
Service of analytical processing of texts in Ukrainian language

# Інсталяція
## 1.1 SQL-only
### 1.1.1 PostgreSQL
1. Install [PostgreSQL](https://www.postgresql.org/download/);
2. `CREATE DATABASE cytrus;`
3. `psql cytrus < init_postgre.sql;`
4. `psql cytrus < initial_data.sql;`

### 1.1.2 MySQL
1. Install MySQL;
2. Run init_my.sql;
3. Run initial_data.sql.

## 1.2 Import from Neo4j into SQL
1. Install [Neo4j](https://neo4j.com/download/);
2. Install [PostgreSQL](https://www.postgresql.org/download/) or MySQL;
3. Install Gradle, JDK and Kotlin;
4. Choose right example in [main.kt](/src/main/kotlin/main.kt);
5. Enter your login, password;
6. `./gradlew build`;
7. `./gradlew run`.

# Participants
* [Засько Євгеній](https://github.com/NeProgramist)
* [Ковалишин Олег](https://github.com/ALEGATOR1209)
* [Кучін Владислав](https://github.com/PaIIadium)
* [Романова Вікторія](https://github.com/V1ckeyR)
* [Сімонов Павло](https://github.com/DjBee0312)
