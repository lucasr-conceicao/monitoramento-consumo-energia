## Resumo do projeto
- Projeto em andamento, sendo realizado no curso POSTECH da FIAP, visando a entrega de um sistema Web, com interfaces e APIs para calcular o consumo mensal de energia. Nessa entraga, serão construidas APIs que um portal poderá consumir para apresentar aos usuários os dados de consumo de cada eletrodoméstico. Serão realizados calculos que vão gerar alertas aos usuários. Isso permitirá a criação de um painel de controle, no qual será possível visualizar o consumo de energia de cada aparelho eletrônico e usuário.

## 🔨 Funcionalidades do projeto

- `Funcionalidade 1` <br><br>
`API de cadastro de endereço`: A API tem como objetivo permitir o gerenciamento de informações sobre os endereços cadastrados em nosso sistema. Para cadastrar um endereço, deve ser informada sua rua, número, bairro, cidade e estado válidos e não podem estar preenchidos com brancos ou nulos. Todos os campos são obrigatórios. O sistema também deve gravar os dados no sistema. Receber informações através do Controller em formato HTTP POST.
#### 
- `Funcionalidade 2` <br><br> 
`API de gestão de pessoas`: A API tem como objetivo permitir o gerenciamento de informações sobre as pessoas cadastradas em nosso sistema. Para cadastrar uma pessoa, deve ser informado seu nome, data de nascimento, sexo e grau de parêntesco válidos e não podem estar preenchidos com brancos ou nulos. Todos os campos são obrigatórios. O sistema também deve gravar os dados no sistema. Receber informações através do Controller em formato HTTP POST.
#### 
- `Funcionalidade 3` <br><br>
`API de gestão de eletrodomésticos`: A API tem como objetivo permitir o gerenciamento de informações sobre os eletrodomésticos cadastrados em nosso sistema. Para cadastrar um eletrodoméstico, deve ser informado seu nome, modelo e potência válidos e não podem estar preenchidos com brancos ou nulos. Todos os campos são obrigatórios. O sistema também deve gravar os dados no sistema. Receber informações através do Controller em formato HTTP POST.
#### 
- `Funcionalidade 4` <br><br>
`API de gestão de casas`: A API tem como objetivo permitir o gerenciamento de informações sobre as casas cadastradas em nosso sistema. Para cadastrar uma casa, deve ser informado seu tipo, endereço e a pessoa que mora nessa casa e não podem estar preenchidos com brancos ou nulos. Todos os campos são obrigatórios. O sistema também deve gravar os dados no sistema. Receber informações através do Controller em formato HTTP POST.
#### 
- `Funcionalidade 5` <br><br>
`API de gestão de tipo casa`: Tabela dominio que contém os tipos de casa.
#### 
- `Funcionalidade 6` <br><br>
`API de gestão de parentesco`: A API tem como objetivo permitir o gerenciamento de informações sobre os parentescos cadastrados em nosso sistema. Para cadastrar um parentesco, deve ser informado as duas pessoas que fazem o parentesco e a descrição (ex. pai, mãe, tio, tia, avô, avó...), e não podem estar preenchidos com brancos ou nulos. Todos os campos são obrigatórios. O sistema também deve gravar os dados no sistema. Receber informações através do Controller em formato HTTP POST.

## 🛠️ Modelagem utilizada para o desenvolvimento do sistema até o momento

![image](https://github.com/lucasr-conceicao/monitoramento-consumo-energia/assets/64719344/afc0f789-f7d9-4c34-b35e-eff6e7c2486f)

OBS 1. Não foi desenvolvido a entidade usuário.
OBS 2. Para rodar a aplicação, esta configurado o banco H2. Na aplicação tem um script que vai carregar a tabela de dominio Tipo_Casa 

1. #### Endereço - Casa: uma Casa pode ter somente um endereço
2. #### Tipo Casa - Casa: cada Casa pode ter somente um tipo
3. #### Eletrodomestico - Casa: cada casa pode ter vários eletrodomesticos
4. #### Pessoa - Casa: cada pessoa pode estar relacionada apenas a uma casa. E uma casa pode ter várias pessoas.
5. #### Pessoa - Parentesco: cada parentesco tem que ter duas Pessoas

## 🛠️ Exemplo Json/Rotas de cada API

###  API Endereços

- `(POST) API de cadastro de endereço`:
- `API de cadastro de endereço`: http://localhost:9080/consumo-energia/v1/endereco
```JSON
{
 "rua": "Avenida Paulista",
 "numero": 1865,
 "bairro": "Consolação",
 "cidade": "São Paulo",
 "estado": "São Paulo"
}
```

- `(PUT) API de cadastro de endereço`:
- `API de cadastro de endereço`: http://localhost:9080/consumo-energia/v1/endereco/70eafe16-9d34-4d2a-a817-8eb2caae1545
```JSON
{
 "rua": "Avenida Celestino Bourroul",
 "numero": 358,
 "bairro": "Limão",
 "cidade": "São Paulo",
 "estado": "São Paulo"
}
```

- `(GET) API de cadastro de endereço`:
- `API de cadastro de endereço`: http://localhost:9080/consumo-energia/v1/endereco/70eafe16-9d34-4d2a-a817-8eb2caae1545
<br><br>
- `(DELETE) API de cadastro de endereço`:
- `API de cadastro de endereço`: http://localhost:9080/consumo-energia/v1/endereco/70eafe16-9d34-4d2a-a817-8eb2caae1545


### API Pessoas
- `(POST) API de gestão de pessoas`:
- `Rota POST - pessoas`: http://localhost:8080/consumo-energia/v1/pessoa
```JSON
{
 "nome": "Lucas Rocha Conceição",
 "cpf": "47852526384",
 "data_nascimento": "1998",
 "genero": "Masculino",
 "email": "lucas@gmail.com",
 "idade": "25"
}
```

- `(PUT) API de gestão de pessoas`:
- `Rota PUT - pessoas`: http://localhost:8080/consumo-energia/v1/pessoa/c2366833-0aaf-4d29-ab35-5e729f6cbd13
```JSON
{
 "nome": "Lucas Rocha Conceição",
 "cpf": "47852526384",
 "data_nascimento": "1998",
 "genero": "Masculino",
 "email": "lucas@outlook.com.br",
 "idade": "25"
}
```
- `(GET) API de buscar pessoa`:
- `Rota GET - pessoas`: http://localhost:8080/consumo-energia/v1/pessoa/c2366833-0aaf-4d29-ab35-5e729f6cbd13
  <br><br>
- `(DELETE) API de deletar pessoa`:
- `Rota DELETE - pessoas`: http://localhost:8080/consumo-energia/v1/pessoa/c2366833-0aaf-4d29-ab35-5e729f6cbd13


### API Eletrodomésticos
- `(POST) API de cadastrar de eletrodomésticos`:
- `Rota POST - eletrodoméstico`: http://localhost:8080/consumo-energia/v1/eletrodomestico/
```JSON
{
 "nome": "Geladeira",
 "id_casa": "004a324e-0169-4260-b3c5-8cc04670ddbd",
 "potencia": 110.0
}
```

- `(PUT) API de atualizar eletrodomésticos`:
- `Rota PUT - eletrodoméstico`: http://localhost:8080/consumo-energia/v1/eletrodomestico/6261cbbf-5b04-4ba9-b60d-a58cdbaa82c1
```JSON
{
 "nome": "Geladeira",
 "id_casa": "004a324e-0169-4260-b3c5-8cc04670ddbd",
 "potencia": 130.0
}
```
- `(GET) API de buscar eletrodoméstico`:
- `Rota GET - eletrodoméstico`: http://localhost:8080/consumo-energia/v1/eletrodomestico/6261cbbf-5b04-4ba9-b60d-a58cdbaa82c1
  <br>
  <br>
- `(DELETE) API de deletar eletrodoméstico`:
- `Rota DELETE - eletrodoméstico`: http://localhost:8080/consumo-energia/v1/eletrodomestico/6261cbbf-5b04-4ba9-b60d-a58cdbaa82c1




2. #### Rotas de cada API:
- `API de cadastro de endereço`: http://localhost:9080/consumo-energia/v1/endereco
- `API de gestão de pessoas`: http://localhost:9080/consumo-energia/v1/pessoa
- `API de gestão de eletrodomésticos`: http://localhost:9080/consumo-energia/v1/eletrodomestico
