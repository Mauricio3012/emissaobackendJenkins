# **Projeto de Automação Brasilseg Back-End**
## **Emissão**

### **Disponível no projeto**:

1. Cotação Back-End
2. Subscrição Back-End

### **Passo-a-passo para execução**:

1. Clone o repositório
2. Importe o repositório no Eclipse através de "Existing maven projects"
3. Com o projeto importado, entre em src/test/java - br.com.automation - emissao_backend
4. Clique no arquivo Runner_Emissao.java 
5. Para determinar qual cenário executar, é preciso selecionar a tag correspondente, da seguinte forma:
 - quando acessar a pasta br.com.automation , entre na pasta features.
 - selecione o cenário que deseja executar e abra o arquivo .feature .
 - Dentro do arquivo .feature , cada cenário possui uma tag no padrão "@texto". Exemplo de tag "@RetornoVigencia".
 - Verificado qual a tag do cenário que deseja executar, anote ela.
6. Voltando ao arquivo Runner_Emissao.java , localize a informação " tags = {" "}," e coloque a tag correspondente.
7. Com a classe aberta, vá em RUN - RUN AS - JUnit Test
8. O teste deverá ser executado sem problemas.
