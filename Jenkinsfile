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
	  
	  stage('Gerando Relatorios') {
		 steps {
			script {
            allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
            ])
    }
    }
}

     stage('Testes Emissao Back-End') {
         steps {
            dir('testes_backend') {

            git credentialsId: '53ffb4e5-180d-419b-beba-692c3dccb2b4', url: 'https://gitlab.srv-cld.brasilseg.com.br/automacao-ultron/emissao/automacao_emissao_back-end'
            bat "mvn test"
         }
         }

      }
      
           stage('Testes Emissao Front-End') {
         steps {
            dir('testes_front) {

            git credentialsId: '53ffb4e5-180d-419b-beba-692c3dccb2b4', url: 'https://gitlab.srv-cld.brasilseg.com.br/automacao-ultron/emissao/automacao_emissao_frontend'
            bat "mvn test"
         }
         }

      }
 
   }
}