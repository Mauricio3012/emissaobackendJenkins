#language: pt
@testesAPI @Consulta
Funcionalidade: Validação dos endpoints da Cotação da Squad de Emissão

  Contexto: 
    Dado que emiti uma proposta pela Cotação

  @RetornoVigencia @Vigencia @geral @cotacao
  Cenario: Consultar Vigências da Cotação
    Quando carrego a URL de "Retornar a Vigência"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E as vigências são retornadas
 
  @RegistraVigencia @Vigencia @geral @cotacao
  Cenario: Registrar Vigência da Cotação
  	Quando retorno a vigência
    E carrego a URL de "Registrar a Vigência"
    E envio o comando POST
    Então verifico se a vigência é registrada corretamente

  @RetornoPeriodicidade @Periodicidade @geral @cotacao
  Cenario: Consultar Periodicidade da Cotação
    Quando carrego a URL de "Retornar a Periodicidade"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E as periodicidades são retornadas

  @RegistraPeriodicidade @Periodicidade @geral @cotacao
  Cenario: Registrar Periodicidade da Cotação
    Quando retorno a periodicidade
    E carrego a URL de "Registrar a Periodicidade"
    E envio o comando POST
    Então verifico se a periodicidade é registrada corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    
  @ConsultaHistoricoCliente @geral @cotacao
  Cenario: Consultar Histórico do Cliente
    Quando carrego a URL de "Retornar a Consulta de Historico"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos
    E o histórico do cliente é retornado

  @CopiarEndereço @Endereço @geral @cotacao
  Cenario: Copiar Endereço
    Quando carrego a URL de "Copiar Endereço"
    E envio o comando POST para Copiar Endereço
    Então verifico na Consulta de Cotação se o endereço foi copiado corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
