# Web Crawler OLX

Web Crawler OLX é um projeto criado com o objetivo de fazer a raspagem de dados no site da OLX, é possível definir o nome do produto, o estado e região.

Além da raspagem também é enviado por e-mail um relatório CSV com os produtos que estão abaixo da média de valor, assim como o de menor valor e maior valor.

Os e-mails são definidos através de um CRUD PostgreSQL.


## Funcionalidades do projeto

- `Raspagem de dados`: Realiza a raspagem de dados no site da OLX.
- `Enviar e-mails`: Envia um relatório com CSV por e-mail.
- `CRUD e-mails`: A lista de e-mail é persistida no PostgreSQL e possui um CRUD para manipulação.

## Tecnologias utilizadas

- `Groovy`
- `PostgreSQL`
- `Gradle`

## Abrir e rodar o projeto

**Executar pela IDE**
- Tenha o Java 8, Groovy 2.4 e PostgreSQL instalados.
- Clone esse repositório.
- Execute o arquivo webCrawler.sql no PostgresSQL.
- Configure o acesso ao banco de dados na classe Connection dentro do package Models.
- A classe responsável por iniciar a aplicação é a Main.
- O projeto vêm com um e-mail configurado, não sei exatamente por quanto funcionará esse e-mail, caso pare de funcionar ou deseje usar outro e-mail, basta editar o login e password na classe SendEmail, caso não seja um e-mail da Microsoft, outras configurações do SMTP terão que ser alteradas.
- Manipule os e-mails pelo CRUD, a aplicação não possui nenhum e-mail cadastrado inicialmente.

<div align="center">
    <img src="https://i.ibb.co/pfC6BBX/Screenshot-from-2023-02-11-20-26-47.png" />
    <img src="https://i.ibb.co/M8Sm9xx/Screenshot-from-2023-02-11-20-28-07.png" />
</div>