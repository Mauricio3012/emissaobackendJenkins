#language: pt
@testesAPI @Subscrição
Funcionalidade: Testes do Back-end do Configurador de Regras

  Contexto: 
    Dado que o Token é gerado

  @ConfiguradorRegras @ValidaçãoMomento
  Cenario: Validação do Endpoint de Momento com o Banco de Dados
    Quando carrego a URL de "Momento"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas

  @ConfiguradorRegras @ValidaçãoMomento @ValidaçãoMomentoFront @ValidacaoComFront
  Cenario: Validação do Endpoint de Momento com o Front
    Quando carrego a URL de "Momento"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas com o Front-End

  @ConfiguradorRegras @ValidaçãoTipoSeguro
  Cenario: Validação do Endpoint de Tipo de Seguro com o Banco de Dados
    Quando carrego a URL de "Tipo de Seguro"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas

  @ConfiguradorRegras @ValidaçãoTipoSeguro @ValidaçãoTipoSeguroFront @ValidacaoComFront
  Cenario: Validação do Endpoint de Tipo de Seguro com o Front
    Quando carrego a URL de "Tipo de Seguro"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas com o Front-End

  @ConfiguradorRegras @ValidaçãoReferência
  Cenario: Validação do Endpoint de Referência com o Banco de Dados
    Quando carrego a URL de "Referência"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas

  @ConfiguradorRegras @ValidaçãoOperação
  Cenario: Validação do Endpoint de Operação com o Banco de Dados
    Quando carrego a URL de "Operação"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas

  @ConfiguradorRegras @ValidaçãoAção
  Cenario: Validação do Endpoint de Ação com o Banco de Dados
    Quando carrego a URL de "Ação"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas

  @ConfiguradorRegras @ValidaçãoAção @ValidaçãoAcaoFront @ValidacaoComFront
  Cenario: Validação do Endpoint de Ação com o Front
    Quando carrego a URL de "Ação"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas com o Front-End

  @ConfiguradorRegras @ValidaçãoMotivoRecusa
  Cenario: Validação do Endpoint de Motivo de recusa com o Banco de Dados
    Quando carrego a URL de "Motivo de recusa"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas

  @ConfiguradorRegras @ValidaçãoMotivoRecusa @ValidaçãoMotivoRecusaFront @ValidacaoComFront
  Cenario: Validação do Endpoint de Motivo de recusa com o Front-End
    Quando carrego a URL de "Motivo de recusa"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas com o Front-End

  @ConfiguradorRegras @ValidaçãoClasseRegra
  Cenario: Validação do Endpoint de Classe da regra com o Banco de Dados
    Quando carrego a URL de "Classe da regra"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas

  @ConfiguradorRegras @ValidaçãoClasseRegra @ValidaçãoClasseRegraFront @ValidacaoComFront
  Cenario: Validação do Endpoint de Classe da regra com o Front-End
    Quando carrego a URL de "Classe da regra"
    E envio o comando GET
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 5000 milisegundos
    E valido se as informações apresentadas estão corretas com o Front-End
