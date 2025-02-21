# language: pt
Funcionalidade: API - Clientes

  Fundo:
    Dado que eu tenho os dados de um cliente

  Cenário: Cadastrar um novo cliente
    Quando enviar requisição para cadastrar o cliente
    Então o cliente deve ser cadastrado com sucesso

  Cenário: Buscar clientes por nome
    Quando enviar requisição para buscar clientes por nome
    Então a resposta deve conter os clientes buscados por nome

  Cenário: Buscar clientes por cpf
    Quando enviar requisição para buscar clientes por cpf
    Então a resposta deve conter os clientes buscados por cpf

  Cenário: Buscar clientes por cep
    Quando enviar requisição para buscar clientes por cep
    Então a resposta deve conter os clientes buscados por cep

  Cenário: Atualizar um cliente
    Dado que eu tenho os dados de um cliente com cpf
    Quando enviar requisição para atualizar o cliente
    Então a resposta deve conter o cliente atualizado

  Cenário: Remover um cliente
    Dado que eu tenho os dados de um cliente com cpf
    Quando enviar requisição para remover o cliente
    Então o cliente deve ser removido