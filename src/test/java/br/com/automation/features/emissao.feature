#language: pt
@testesAPI @Consulta
Funcionalidade: Validação dos endpoints de Consulta da Squad de Emissão

	@GerarToken
  Cenario: Gerar Token
  Dado carrego a URL de "Gerar Token"
  Quando envio o comando POST para produto
  Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

  @ConsultaProponente @ConsultaApólice @emissao
  Cenario: Verificar se API de Consulta de Contrato por Proponente está no ar
    Dado carrego a URL de "Consulta por Proponente"
    Quando envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

  @ConsultaContrato @ConsultaApólice @emissao
  Cenario: Verificar se API de Consulta de Contrato por Contrato está no ar
    Dado carrego a URL de "Consulta por Contrato"
    Quando envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

  @ConsultaProposta @ConsultaApólice @emissao
  Cenario: Verificar se API de Consulta de Contrato por Proposta está no ar
    Dado carrego a URL de "Consulta por Proposta"
    Quando envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

  @ConsultaApólice @emissao
  Cenario: Verificar se API de Consulta de Contrato por Apólice está no ar
    Dado carrego a URL de "Consulta por Apólice"
    Quando envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

  @FiltrarEndereço @Endereço @emissao
  Cenario: Filtrar Endereço
    Dado que emiti uma proposta pela Cotação para um cenário de "Endereço"
    Quando carrego a URL de "Filtrar Endereço"
    E envio o comando GET
    Então o endereço é filtrado corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    
