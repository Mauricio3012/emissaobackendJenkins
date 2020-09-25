#language: pt
@testesAPI @Cotação
Funcionalidade: Fluxo de Cotação End-to-End Backend

  Contexto: 
    Dado que o Token é gerado

  @FluxoCotacaoComProponente
  Cenario: Fluxo de Cotação Backend com Proponente
    Quando carrego a URL de "Iniciar Cotação com Proponente"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Oferta"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Incluir Objeto Segurado"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Incluir Beneficiário"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Incluir Proponente"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Periodicidade de Contratação"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Plano"
    E envio o comando POST para o fluxo de cotação   
    E carrego a URL de "Selecionar Plano de Pagamento"
    E envio o comando POST para o fluxo de cotação  
    E carrego a URL de "Selecionar Assistência"
    E envio o comando POST para o fluxo de cotação  
    E carrego a URL de "Selecionar Parcelas"
    E envio o comando POST para o fluxo de cotação   
    E carrego a URL de "Registrar De acordo"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Contrato"
    E envio o comando POST para o fluxo de cotação
    Então a proposta é gerada corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos
    
    @FluxoCotacaoComProponenteHML
   Cenario: Fluxo de Cotação Backend com Proponente
    Quando carrego a URL de "Iniciar Cotação com Proponente HML"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Oferta HML"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Incluir Objeto Segurado HML"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Periodicidade de Contratação HML"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Plano HML"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Registrar De acordo HML"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Assistência HML"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Grava Contrato HML"
    E envio o comando POST para o fluxo de cotação
    Então o status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos
    
  @FluxoCotacaoComProponenteDoisObjetos
  Cenario: Fluxo de Cotação Backend com Proponente
    Quando carrego a URL de "Iniciar Cotação com Proponente"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Oferta"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Incluir Objeto Segurado"
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
      
      @FluxoCotacaoComProponenteMassaComDoisObjetos
  Esquema do Cenario: Fluxo de Cotação Backend com Proponente
    Quando carrego a URL de <tipo>
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Selecionar Oferta"
    E envio o comando POST para o fluxo de cotação
    E carrego a URL de "Incluir Objeto Segurado"
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

      
