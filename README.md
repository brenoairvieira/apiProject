# apiProject
Teste API REST - SpringBoot

-Como usar os Serviços

Os serviços (GET, POST, PUT, DELETE) podem ser utilizados através do caminho /clientes
Exemplo: 

GET http://localhost:8080/clientes - Consulta todos os clientes

  Exemplo de response: 

  [
  
    {
    
        "codigo": 1,
        
        "nome": "BRENO1",
 
        "idade": 24,
       
       "temperaturaMinima": 7.58,
       
       "temperaturaMaxima": 13.02
       
    },
    
    {
    
        "codigo": 2,
        
        "nome": "BRENO2",
        
        "idade": 25,
        
        "temperaturaMinima": 7.58,
        
        "temperaturaMaxima": 13.02
    
    }

]

GET http://localhost:8080/clientes/1 - Consulta cliente de id = 1

POST http://localhost:8080/clientes - Cria um cliente 

  Exemplo de request: 
  
    {
  
    "nome": "BRENO3",
  
    "idade": 24
    
    }
  
PUT http://localhost:8080/clientes/1 - Altera um cliente de id = 1  
 
 Exemplo de request
 
 {
 
   "nome": "BRENO3",

   "idade": 24

 }

-Ferramentas Utilizadas

  IDE - Eclipse
  
  Banco de dados - PostgreSQL
  
  PgAdmin 4 
  
  Projeto realizado com SpringBoot
  
  Postman para acessar os serviços da API
  
-O deploy da aplicação pode ser realizado em um servidor tomcat utilizando o .war gerado (clean package) da mesma

-O Schema do banco de dados necessário para a aplicação se encontra no arquivo schema.sql dentro da própria aplicação



