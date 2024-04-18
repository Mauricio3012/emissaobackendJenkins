pipeline {
    agent any
    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }
   stages {
      stage('teste API') {
         steps {
            bat 'mvn test'
         }
      }
   }
}