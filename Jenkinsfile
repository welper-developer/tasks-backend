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
        stage ('Unit tests'){
            steps {
                sh 'mvn test'
            }
        }
        
    }
}