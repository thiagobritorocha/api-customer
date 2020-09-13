# API-Customer
![Maturidade](https://github.com/thiagobritorocha/api-customer/blob/master/customer-success.jpg) 

# Um bom lugar para cadastrar seus clintes:customs: e não perder a chance de novos negócios! :arrow_upper_right:

Comandos para subir serviço api-customer

# DOCKER

docker run -it -d -p 8080:8080 thiagobritorocha/api-customer:latest

# KUBERNATES

kubectl apply -f deployment-api-customer.yaml
kubectl apply -f service-api-customer.yaml

# COMPOSE

docker-compose up thiagobritorocha/api-customer:latest

# To Do

- Adicionar service config;
- Alterar banco em memória para banco dado em disco;
- Alterar autenticação basic para OAUTH2...

# Documentação e acesso a API-Customer em host:port/swagger-ui.html
