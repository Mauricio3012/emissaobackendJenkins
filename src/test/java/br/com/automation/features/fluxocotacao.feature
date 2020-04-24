#language: pt
@testesAPI @Cotação
Funcionalidade: Fluxo de Cotação End-to-End Backend

  Contexto: 
    Dado que o Token é gerado

  @FluxoCotacao
  Cenario: Cotação
    Quando carrego a URL de "Iniciar Cotação"
    E envio o comando GET
    E carrego a URL de "Consulta GrupoRamo"
    E envio o comando GET
    E carrego a URL de "Consulta Oferta"
    E envio o comando GET
    E carrego a URL de "Consulta Plano"
    E envio o comando GET
    E carrego a URL de "Grava Plano"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Vigência"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Consulta Pessoa"
    E envio o comando GET
    E carrego a URL de "Grava Pessoa"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Consulta CEP"
    E envio o comando GET
    E carrego a URL de "Grava Endereço"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Email"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Telefone"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Objeto Segurado"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Contrato"
    E envio o comando POST para o fluxo de cotação
    #E carrego a URL de "Gerar PDF"
    #E envio o comando GET
    Então a proposta é gerada corretamente
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

  @FluxoCotacao2
  Cenario: Cotação
    Quando realizo o fluxo de cotação
    E confirmo a contratação
    Então a proposta é gerada corretamente
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos

  @FluxoCotacaoComProponente
  Cenario: Fluxo de Cotação Backend com Proponente
    Quando carrego a URL de "Iniciar Cotação com Proponente"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Oferta"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Incluir Objeto Segurado"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Periodicidade de Contratação"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Plano"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Registrar De acordo"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Assistência"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Contrato"
    E envio o comando POST para o fluxo de cotação
    Então a proposta é gerada corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos

  @FluxoCotacaoComProponenteMassa
  Esquema do Cenario: Fluxo de Cotação Backend com Proponente
    Quando carrego a URL de <tipo>
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Oferta"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Incluir Objeto Segurado"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Periodicidade de Contratação"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Plano"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Registrar De acordo"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Contrato"
    E envio o comando POST para o fluxo de cotação
    Então a proposta é gerada corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

    Exemplos: 
      | tipo                             |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
      | "Iniciar Cotação com Proponente" |
