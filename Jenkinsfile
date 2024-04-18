pipeline {
    agent any
    tools {
        maven 'MAVEN'
        jdk 'JAVA_JDK'
    }
   stages {
      stage('Testes de API') {
         steps {
            bat "mvn test"
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
                    results: [[path: 'target/allure-results'], [path: 'testes_front_emissao/target/allure-results']]
            ])
            
        }
    }
   
    }
    
     }
     }
}