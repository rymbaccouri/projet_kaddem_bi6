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
                sh "docker build -t kaddem-0.0.1 ."
              }
            }

       stage("Docker Hub") {
    steps {
        script {
            def dockerConfigDir = "/var/jenkins_home/.docker"  

            sh "mkdir -p $dockerConfigDir"
            sh "echo '{\"auths\": {\"https://index.docker.io/v1/\": {\"auth\": \"${DockerHubCredentials}\"}}}' > $dockerConfigDir/config.json"

            sh "docker push baccouri/kaddem-0.0.1"
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
