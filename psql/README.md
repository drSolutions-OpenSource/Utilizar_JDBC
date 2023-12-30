<h1 align="center">Usar o Java Database Connectivity (JDBC) com o banco de dados PostgreSQL</h1>
<p align="center"><i>Repositório para armazenar as códigos em Java criados para exemplificar a utilização do JDBC (Java Database Connectivity) com o banco de dados PostgreSQL, através do ElephantSQL - PostgreSQL as a Service</i></p>

## Sobre esse Projeto

Foi criada uma demonstração da utilização do Java Database Connectivity (JDBC) com o banco de dados
PostgreSQL, com as instruções utilizadas em um CRUD (Create, Read, Update, Delete), ou seja, as instruções
SQL INSERT, SELECT, UPDATE e DELETE.<br/><br/>
Quanto as tabelas utilizadas, temos a 'usuarios' com os campos id, nome, email, login e senha, aém da 
tabela 'telefones', com os campos id, tipo, telefone e usuariosid.<br/><br/>
A tabela 'telefones' utiliza uma FK com a tabela 'usuarios', através do campo usuariosid.<br/><br/>
As instruções SQL para a criação das sequências e das tabelas estão na pasta SQL deste repositório.<br/><br/>
Utilizou-se a plataforma ElephantSQL - PostgreSQL as a Service para a criação destes testes e seus testes.

## Tecnologias Utilizadas

<p display="inline-block">
    <img width="60" src="https://cdn.icon-icons.com/icons2/2415/PNG/512/java_original_wordmark_logo_icon_146459.png" alt="Java"/>
</p>

## Ferramentas de Desenvolvimento

<p display="inline-block">
    <img width="48" src="https://cdn.freebiesupply.com/logos/large/2x/eclipse-11-logo-png-transparent.png" alt="Eclipse IDE"/>
</p>

## Assuntos Disponíveis para Consulta

<li>Java Database Connectivity (JDBC)</li>

## Licença

Copyright 2023 drSolutions - Agência Digital, Licensed under the Apache License, Version 2.0 (the "License"); you may
not use this file except in compliance with the License. You may obtain a copy of the License at

<a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.

## FAQ

**Quem pode utilizar esse software?**

Qualquer pessoa pode utilizar, basta possuir uma estação de trabalho com o Java Development Kit instalado.

**Quem pode contribuir?**

Qualquer desenvolvedor pode contribuir, sendo muito bem-vindo! Caso queira contribuir, veja nosso Código de Conduta
| [Contributor Covenant Code of Conduct](CODE_OF_CONDUCT.md).

## Referências

<p display="inline-block">
    <a href="https://www.java.com/pt-BR/">Java | Oracle</a><br/>
    <a href="https://www.oracle.com/br/java/technologies/downloads/">Java Development Kit</a><br/>
    <a href="https://openjdk.org/">OpenJDK</a><br/>
    <a href="https://www.elephantsql.com/">ElephantSQL - PostgreSQL as a Service</a><br/>
    <a href="https://www.jdevtreinamento.com.br/">JDev Treinamento</a>
</p>

## Autor

<a href="https://www.linkedin.com/in/diegomendesrodrigues/">Diego Mendes Rodrigues</a>
