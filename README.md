# Projeto Sem Barrar

### Contexto da aplicação:

Esta aplicaçao é uma atividade proposta pelas disciplinas de `Projeto Backend, Microsserviços e NoSQL` e `Banco de dados NoSQL`, ambas ministradas pelo ministrada pelo Professor Doutor [Camilo Barreto](https://www.linkedin.com/in/camilobarreto/), do curso de do curso de `Sistemas para Internet` do `Instituto Federal de Educação, Ciência e Tecnologia do Triângulo Mineiro - IFTM`.

O projeto consiste no desenvolvimento de uma aplicação com arquitetura de miscroserviços, baseado em um estudo de caso existente a fim de exercitar os conceitos discutidos em sala.

A criação do projeto `Sem Barrar` foi realizada tendo observado o modelo de negócio de um famoso serviço de tags veiculares para utilização em pedágios e estacionamentos.

A arquitetura é composta por quatro micoserviços `(Client, Establishment, Tag e Log)`, sendo que, três deles (Client, Establishment, Tag) se comunicam entre si utilizando o serviço Spring Cloud Open Feign, e se comunicam com o serviço de `event driven` (Log) através da comunicação via mensageria com o RabbitMQ.

### Tecnologias utilizadas:

- Back-End:
    * Java
    * Spring Boot 
        * Spring Validation
        * Spring AMQ
        * Spring Data JPA
        * Spring Cloud Gateway
        * Spring Cloud Eureka
        * Spring Cloud Open Feign
    * MongoDB
    * RabbitMQ
    * Maven
    * Lombok
    * Postman

### Diagrama de Casos de Uso

Este diagrama busca representar a interação do sistema como um todo:

![Diagrama Casos de Uso](diagrams/diagrama%20uc-sem-barrar.drawio.png)