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
	  
	  stage('reports') {
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
 
   }
}