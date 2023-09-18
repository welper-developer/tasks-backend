pipeline {
    agent any
    tools {
        maven '3.8.1'
    }
    stages {
        stage ('Build Backend'){
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        
    }
}