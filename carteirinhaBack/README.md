# Back end

## Introdução

Para desenvolver o back end, utilizamos o spring boot, para criar um servlet em que fizemos deploy na GCP e para gerenciar as dependencias do projeto, utilizamos o maven.
O codigo aqui presente, foi desenvolvido em java 8, tentamos ao maximo não utilizar ferramentas de versões mais novas do java a fim de manter essa retrocompatibilidade
pois o objetivo original era de fazer deploy da aplicação no ambiente standard java8 do app engine na GCP, por fim, nosso produto final ficou maior do que esperavamos
e atingimos o limite de espaço, tendo que utilizar um ambiente standard do java 11 mas com runtime configurado para o jdk8.

## Estrutura do back end

![Back end arch](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/planejamento-e-diagramas/arch.jpg?raw=true)

O back end pode ser dividido em 4 componentes principais:

### [Controller]([src/main/java/com/unicamp/controller](https://github.com/Necctares/MC855---Carteirinha-Estudantil/tree/main/carteirinhaBack/src/main/java/com/unicamp/controller))

Componente responsavel por gerenciar as requests do front end. Adotamos um padrão de comunicação via JSON com o front end, todas as chamadas são feitas recebendo e
enviando arquivos do tipo JSON com um padrão de resposta, onde no arquivo enviado temos os campos:

- Response
- Message
- Object (Opcional)

A comunicação é feita por HTTP request, utilizando os metodos POST e GET.

### [Service](https://github.com/Necctares/MC855---Carteirinha-Estudantil/tree/main/carteirinhaBack/src/main/java/com/unicamp/service)

O componente service foi desenvolvido para estabelecer a logica da aplicação, ela é o business layer e fica encarregado de fazer as checagens corretas de todas as
logicas de negocio envolvendo as requests do front e o que será passado de resposta. Nele, as entidades são envelopadas em ObjectNodes para serem enviados para o front.

### [DAO](https://github.com/Necctares/MC855---Carteirinha-Estudantil/tree/main/carteirinhaBack/src/main/java/com/unicamp/dao)

Esta componente serve para gerenciar os serviços do banco de dados, nele estão implementados as querys necessarias para pegar os dados do banco.

### [Entity](https://github.com/Necctares/MC855---Carteirinha-Estudantil/tree/main/carteirinhaBack/src/main/java/com/unicamp/entity)

Componente que serve como entidade do DB. Nelas são descritas todas as entidades em que o spring boot transformará em tabelas no DB.

### Utilitarios

Para melhor implementar a aplicação desenvolvemos algumas ferramentas utilitarias, são elas:

- [VO](https://github.com/Necctares/MC855---Carteirinha-Estudantil/tree/main/carteirinhaBack/src/main/java/com/unicamp/vo) -> Os values objects foram criados no intuito
de servirem como wrappers para as classes de entidades, são uteis quando precisamos retornar dados de uma entidade para o front mas não queremos incluir alguns deles.
- [Utils](https://github.com/Necctares/MC855---Carteirinha-Estudantil/tree/main/carteirinhaBack/src/main/java/com/unicamp/Utils) -> Esta pasta contem classes auxiliares
para o desenvolvimento da aplicação, a classe [JsonMessage](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/carteirinhaBack/src/main/java/com/unicamp/Utils/JsonMessage.java), responsavel por envelopar as respostas que serão enviadas para o front
e a classe [AuthCheck](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/carteirinhaBack/src/main/java/com/unicamp/Utils/AuthCheck.java) responsavel
por fazer as checagens de segurança da aplicação, onde implementamos uma logica de segurança simples apenas para testes.

### Auth

Mockamos um sistema basico de autentificação com tokens que são gerados por chamada no controller da entidade oAuth, para ativar o update de tokens no banco
é preciso adicionar uma chave [neste arquivo](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/carteirinhaBack/src/main/resources/applicationValues.properties)
no campo 'original.value', este valor será requisitado na request "/update" do oAuth como RequestParam.

## Configuração

Inicialmente estavamos rodando a aplicação em localhost para fim de desenvolvimento, porém, no fim do projeto migramos a aplicação para a cloud e fizemos algumas
mudanças que impactaram no funcionamento do localhost. Anteriormente tinhamos implementado um pequeno servlet com uma interface web modesta para servir de admin
console, porém, ao migrarmos para a cloud decidimos remover esta parte da implementação, deixando a aplicação mais enxuta.

Ao inicializar o projeto, utilize o comando 'mvn package' e o maven irá baixar todas as dependencias do projeto, caso não possua o maven instalado, baixe-o antes.

Para fazer o deploy na cloud, basta configurar [este arquivo](https://github.com/Necctares/MC855---Carteirinha-Estudantil/blob/main/carteirinhaBack/src/main/resources/application.properties)
com as informações pedidas entre as chaves { }.

O serviço de transações pix está, atualmente, funcionando no ambiente de homologação do Banco do Brasil, assim não é necessário realizar nenhum tipo de configuração de contas bancárias para o seu funcionamento.
