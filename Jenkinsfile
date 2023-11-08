pipeline {
    agent any

    stages {
        stage('git') {
            steps {
                echo 'Pulling... '
                git branch: 'Departement',
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

            sh "mvn sonar:sonar -Dsonar.login=squ_55feb140a6d79553cce5f23bde7e98257df04a2f"
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
                       sh 'docker build -t ousse/alpine:1.0.0 .'
                   }
               }
           }
   stage('Docker Login') {
               steps {
   				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u="ousse" -p="dockerhub" '
   			}
   		}
   	 stage('Push DockerHub') {
                steps {
   		    sh 'docker push ousse/alpine:1.0.0 '
   			}
   	    post {
   		always {
   			sh 'docker logout'
   		}
           	}
     }
          	stage('Docker compose') {
                 steps {
                     sh 'docker compose build'
                     sh 'docker compose up -d'
     	    }	}

    }
}