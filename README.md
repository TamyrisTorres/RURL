# RURL
Encurtador de URL

Este projeto é uma API para encurtamento de URLs, 
desenvolvida com Java 21, Spring Boot e MySQL. 
Inclui também uma página HTML para testar manualmente a funcionalidade.
O banco de dados é executado em um container Docker, facilitando a
configuração e o ambiente de desenvolvimento.

## 🧱 Pré-requisitos

- [Docker Desktop](https://docs.docker.com/desktop/) instalado e em execução.

## 🚀 Como rodar

1. Clone o repositório:

```bash
git clone https://github.com/TamyrisTorres/RURL.git
cd RURL
```

2. Compile e suba o banco de dados com Docker:

```bash
docker-compose up --build
```

3. Suba a aplicação pela ide de sua preferência (como IntelliJ ou Eclipse).


---------------------------------------------------------

🧪 Testes manuais
Após a aplicação rodando (porta padrão: 8080), use os links abaixo:

✅ Swagger
👉 http://localhost:8080/swagger-ui.html

🖼️ Interface HTML 
👉 http://localhost:8080/index.html

------

## Fique à vontade para contribuir, reportar bugs ou sugerir melhorias.

------