pipeline {
    agent any

    stages {
        stage('Github') {
            steps {
                echo 'Pulling... '
                git branch: 'GestionUnversite',
                url: 'https://github.com/rymbaccouri/projet_kaddem_bi6.git'
            }
        }

        stage('Clean Project with Maven') {
            steps {

                sh 'mvn clean'
            }
        }

        stage('Compile Project with Maven') {
            steps {

                sh 'mvn compile'
            }
        }
        stage('SONARQUBE') {
    steps {

        script {

            sh "mvn sonar:sonar -Dsonar.login=squ_63b472822addfeb3951102a7d7de926400e3ea3d"
        }
    }
}
        stage('MOCKITO'){
            steps{
                echo'laching units test ...'
                sh 'mvn test'
            }

        }
stage('Nexus Repository ') {
    steps {
        sh 'mvn deploy'
    }
}


 	stage('Build docker image'){
               steps{
                   script{
                       sh 'docker build -t raouf119/projetkaddem-0.0.1 .'
                   }
               }
           }
   stage('Docker Login') {
               steps {
   				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u="raouf119" -p="raouf123456" '
   			}
   		}
   	 stage('Push Docker Image to DockerHub') {
                steps {
   		    sh 'docker push raouf119/projetkaddem-0.0.1 '
   			}
   	    post {
   		always {
   			sh 'docker logout'
   		}
           	}
     }
          	stage('Build and Start Docker Compose') {
                 steps {
                     sh 'docker compose build'
                     sh 'docker compose up -d'
     	    }	}



    }
}