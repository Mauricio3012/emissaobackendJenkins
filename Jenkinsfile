pipeline {
    agent any
    tools {
        maven 'MAVEN'
        jdk 'JAVA_JDK'
    }
   stages {
      stage('teste API') {
         steps {
            bat 'mvn test'
         }
      }
   }
}