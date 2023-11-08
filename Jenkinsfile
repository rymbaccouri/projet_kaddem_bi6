pipeline {
    agent any

    stages {
        stage('git') {
            steps {
                echo 'Pulling... '
                git branch: 'gestionContrat',
                url: 'https://github.com/rymbaccouri/projet_kaddem_bi6.git'
            }
        }

        stage('Maven Clean') {
            steps {
                // Étape pour nettoyer le projet avec Maven
                sh 'mvn clean'
            }
        }

        stage('Maven Compile') {
            steps {
                // Étape pour compiler le projet avec Maven
                sh 'mvn compile'
            }
        }
        stage('MVN SONARQUBE') {
    steps {
        // Étape pour compiler le projet avec Maven
        script {

            sh "mvn sonar:sonar -Dsonar.login=squ_04772088a51c9d458dc6577e12e10cb07f50c16b"
        }
    }
}
        stage('JUNIT-MOCKITO'){
            steps{
                echo'laching units test ...'
                sh 'mvn test'
            }
        }
stage('Nexus Deployment') {
    steps {
        sh 'mvn deploy'
    }
}


 	stage('Build docker image'){
               steps{
                   script{
                       sh 'docker build -t elemejri/alpine:1.0.0 .'
                   }
               }
           }
   stage('Docker Login') {
               steps {
   				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u="elemejri" -p="dockerhub" '
   			}
   		}
   	 stage('Push DockerHub') {
                steps {
   		    sh 'docker elemejri/alpine:1.0.0 '
   			}
   	    post {
   		always {
   			sh 'docker logout'
   		}
           	}
     }

    }
}