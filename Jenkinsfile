pipeline {
    agent any

    stages {
        stage('git') {
            steps {
                echo 'Pulling... '
                git branch: 'GestionDepartement',
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

            sh "mvn sonar:sonar -Dsonar.login=squ_e8b74f688ee370d72fba5d8e40af97f5e96d7664"
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

                       sh 'docker build -t back:1.0 .'
                   }
               }
           }
            stage("Docker Hub") {
                               steps{
                                     sh "docker login -u ousse -p dckr_pat_TqG5C38y8Z3pCQ6la-ctfuWyea8"
                                     sh "docker tag back:1.0 ousse/back:1.0"
                                     sh "docker push ousse/back:1.0"
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
