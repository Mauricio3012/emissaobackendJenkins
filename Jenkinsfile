pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }
   stages {
      stage('Testes de API') {
         steps {
            bat "mvn test"
         }
      }
	  
           stage('Testes Funcionais') {
         steps {
            dir('testes_front_emissao') {

            git credentialsId: '53ffb4e5-180d-419b-beba-692c3dccb2b4', url: 'https://gitlab.srv-cld.brasilseg.com.br/automacao-ultron/emissao/automacao_emissao_frontend'
            bat "mvn test -Dplatform=WEB-CHROME -DinputType=EXCEL"
         }
         }

      }
      
       stage('Testes de Performance') {
         steps {
         build job: 'teste_carga'
         }
      }
      
         	 
   }
   
     post {
        always {
            script {
            allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'testes_backend/target/allure-results'], [path: 'testes_backend/target/allure-results'], [path: 'testes_front_emissao/target/allure-results']]
            ])
            perfReport filterRegex: '', sourceDataFiles: 'C:\\Users\\moliveij\\Desktop\\jmeter\\bin\\TestResultadoMauricioReport22.jtl'
            
        }
    }
   
    }
    
     
}