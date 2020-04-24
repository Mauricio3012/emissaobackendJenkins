#language: pt
@geral
Funcionalidade: Cadastro dos Grupo Ramos e Chassi

  @CadastroGrupoRamo @produto
  Cenario: Cadastro dos Grupos Ramo
    Dado carrego a URL de "Grupo Ramo"
    Quando envio o comando POST para produto
    Então verifico o retorno

  @CadastroChassi @produto
  Cenario: Cadastro Chassi
    Dado carrego a URL de "Chassi"
    Quando envio o comando POST para produto
    Então verifico o retorno 

  @CadastroCoberturaIncendio @produto
  Cenario: Cobertura - Incêndio, Idt, Raio e Explosão de QQ Natureza
    Dado carrego a URL de "Cobertura Incêndio"
    Quando envio o comando POST para produto
    Então verifico o retorno

  @CadastroCoberturaVendaval @produto
  Cenario: Cobertura - Vendaval, Furacão, Ciclone, Tornado, Granizo e Fumaça
    Dado carrego a URL de "Cobertural Vendeval"
    Quando envio o comando POST para produto
    Então verifico o retorno

  @CadastroChassiCoberturaIncendio @produto
  Cenario: Cadastro Chassi Cobertura - Incêndio
    Dado carrego a URL de "Cadastro Chassi Incêndio"
    Quando envio o comando POST para produto
    Então verifico o retorno

  @CadastroChassiCoberturaVendaval 
  Cenario: Cadastro Chassi Cobertura - Vendaval
    Dado carrego a URL de "Cadastro Chassi Vendaval"
    Quando envio o comando POST para produto
    Então verifico o retorno
