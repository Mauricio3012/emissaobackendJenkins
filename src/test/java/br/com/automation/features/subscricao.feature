#language: pt
@testesAPI @Subscrição
Funcionalidade: Testes do Grid de Subscrição Backend

 @Subscrição @ComboProduto
  Cenario: Carregar Combo de Produtos
  	Dado que o Token é gerado
		Quando realizo o fluxo de cotação
		E confirmo a contratação
		Então a proposta é gerada corretamente
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos     
    
 @Subscrição @Semaforo
  Cenario: Semáforo
  	Dado que o Token é gerado
		Quando realizo o fluxo de cotação
		E confirmo a contratação
		Então a proposta é gerada corretamente
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos     

 @Subscrição @Filtro
  Cenario: Filtros
  	Dado que o Token é gerado
		Quando realizo o fluxo de cotação
		E confirmo a contratação
		Então a proposta é gerada corretamente
    Entao o status de retorno é 200 e o tempo de resposta é menor ou igual a 10000 milisegundos    