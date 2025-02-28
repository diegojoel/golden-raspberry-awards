# Golden Raspberry API

## Descrição
Esta é uma API RESTful que permite a leitura da lista de indicados e vencedores da categoria Pior Filme do **Golden Raspberry Awards**.

A API fornece um endpoint para obter o produtor com **maior intervalo** entre prêmios consecutivos e o que obteve **dois prêmios mais rapidamente**.

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database** (banco de dados em memória)
- **Maven**
- **JUnit 5** para testes de integração

## Como Executar o Projeto
### 1. Pré-requisitos
- Ter o **JDK 21** instalado e configurado
- Ter o **Maven** instalado

### 2. Acessar o projeto
cd golden-raspberry-api
```

### 3. Executar o projeto
```sh
mvn spring-boot:run
```
A API estará disponível em: **`http://localhost:8080`**

### 4. Acessar o banco de dados H2 (opcional)
Se desejar visualizar os dados carregados, acesse:
```
http://localhost:8080/h2-console
```
Use as seguintes credenciais:
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Usuário**: `sa`
- **Senha**: (deixe em branco)

## Endpoints Disponíveis
### 🎬 **Buscar intervalo de prêmios**
```http
GET v1/awards/intervals
```
**Resposta Exemplo:**
```json
{
  "min": [
    {
      "producer": "Producer 1",
      "interval": 1,
      "previousWin": 2008,
      "followingWin": 2009
    }
  ],
  "max": [
    {
      "producer": "Producer 2",
      "interval": 99,
      "previousWin": 1900,
      "followingWin": 1999
    }
  ]
}
```

## Testes de Integração
Para rodar os testes automatizados, execute:
```sh
mvn test
```
Isso validará se a API retorna os dados corretamente.


