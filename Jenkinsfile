pipeline {
    agent any

    stages {
        stage("Git") {
            steps {
                sh 'git checkout gestion_etudiant '
                sh 'git pull origin gestion_etudiant'
            }
        }

        stage('Maven Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Maven Compile') {
            steps {
                sh 'mvn compile'
            }
        }
        
        stage('MVN SONARQUBE') {
            steps {
                script {
                    sh "mvn sonar:sonar -Dsonar.login=squ_2bf48e91e6296c2a681ecb743886ae70229627a5"
                }
            }
        }

stage('Junit / Mockito') {
    steps {
        sh 'mvn test'
    }
}

stage('Nexus Deployment') {
    steps {
        sh 'mvn deploy'
    }
}
        stage("Docker Image"){
              steps{
                sh "docker build -t baccouri/projet_kaddem_bi6-1.0 ."
              }
            }
            stage('Deploy Docker Image') {
                  steps {
                    withCredentials([string(credentialsId: 'mdp')]) {
                      sh '''
                        docker login 
                        docker push baccouri/projet_kaddem_bi6-1.0
                      '''
                    }
                  }
                }
                stage('Docker Compose') {
                      steps {
                        sh 'docker-compose up -d'
                      }
                    }
    }
}
