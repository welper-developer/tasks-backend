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
        stage (' Quality Gate '){
            steps {
                sleep(5)
                timeout( time: 1, unit: 'MINUTES'){
                    waitForQualityGate abortPipeline : true
                }                
            }
        } 

        stage (' Deploy Backend '){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'Tomcat_login', path: '', url: 'http://tomcat8:8080')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }   

        // stage (' API Test '){
        //     steps{
        //         dir('api-test'){
        //             git 'https://github.com/wcaquino/tasks-api-test'
        //             sh 'mvn test'
        //         }                
        //     }
        // } 

        stage (' Deploy Frontend '){
            steps{
                dir('front-end'){
                    git 'https://github.com/wcaquino/tasks-frontend'
                    sh 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'Tomcat_login', path: '', url: 'http://tomcat8:8080')], 
                    contextPath: 'tasks', 
                    war: 'target/tasks.war'
                }               
            }
        }  

        // stage (' Functional Test '){
        //     steps{
        //         dir('functional-test'){
        //             git 'https://github.com/wcaquino/tasks-functional-tests'
        //             sh 'mvn test'
        //         }                
        //     }
        // } 
        
    }



    
}