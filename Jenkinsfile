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
        stage ('Sonar Analysis'){
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
                steps {
                    withSonarQubeEnv('SONAR_LOCAL'){
                        sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://sonarqube:9000 -Dsonar.login=5158aa0ec15e61a030eaf0c8312827b06f55cd13 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Aplication.java"
                    }
            }
        }
        stage ('Quality Gate'){
            steps {
                timeout( time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline : true
                }                
            }
        }
    }
}