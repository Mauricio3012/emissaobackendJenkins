#language: pt
@testesAPI @Consulta
Funcionalidade: Validação dos endpoints de Beneficiario da Squad de Emissão

  @IncluirBeneficiario @Beneficiario @geral
  Cenario: Incluir Beneficiário
    Dado que emiti uma proposta pela Cotação para um cenário de "Beneficiário"
    Quando carrego a URL de "Beneficiário"
    E envio o comando POST para "Incluir"
    Então verifico se o beneficiario foi "incluido" corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

  @AlterarBeneficiario @Beneficiario @geral
  Cenario: Alterar Beneficiário
    Dado carrego a URL de "Beneficiário"
    Quando envio o comando POST para "Alterar"
    Então verifico se o beneficiario foi "alterado" corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

  @ExcluirBeneficiario @Beneficiario @geral
  Cenario: Excluir Beneficiário
    Dado carrego a URL de "Beneficiário"
    Quando envio o comando POST para "Excluir"
    Então verifico se o beneficiario foi "excluido" corretamente
    E o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos

