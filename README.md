# Desafio NEXDOM - FullStack (Java + VueJS)
## Aplicação RESTful para controle de estoque

### Tecnologias utilizadas:
#### Back-end:
  - Java com Spring Boot e Spring JPA
  - Banco de dados H2
    
### Conexão H2
spring.datasource.url=jdbc:h2:mem:desafionexdom

spring.datasource.driverClassName=org.h2.Driver

spring.datasource.username=sa

spring.datasource.password=123

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

### Frnt-end:
  - VueJS
  - Typescript
  - TailwindCss
  - Para realizar a comunicação com o servidor, foi utilizado o axios
    
### Para rodar a aplicação front-end, vá na pasta do projeto/web:
  - npm install
  - npm run dev
