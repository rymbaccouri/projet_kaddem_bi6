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
            stage("Docker Build") {
            steps {
                script {
                    // Debug : Vérifiez les autorisations Docker
                    sh 'docker info'

                    // Debug : Affichez les fichiers dans le répertoire de construction
                    sh 'ls -la'

                    // Debug : Affichez le contenu du Dockerfile
                    sh 'cat Dockerfile'

                    // Debug : Essayez de construire l'image sans Jenkins pour voir si le problème persiste
                    sh 'docker build -t test-image .'

                    // Construisez votre image Docker
                    sh 'docker build -t baccouri/kaddem-0.0.1 .'
                }
            }
        }

    stage('Docker Login') {
               steps {
   				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u="baccouri" -p="docker123" '
   			}
   		}
   	 stage('Push DockerHub') {
                steps {
   		    sh 'docker push baccouri/kaddem-0.0.1:latest '
   			}
   	    post {
   		always {
   			sh 'docker logout'
   		}
           	}
     }





                stage('Docker Compose') {
                      steps {
                        sh 'docker-compose up -d'
                      }
                    }
pipeline {
    agent any


        stage('Grafana') {
            steps {
                script {
                   
                    sh 'docker stop grafana-container || true'
                    sh 'docker rm grafana-container || true'
                    sh 'docker run -d -p 4003:3000 --name grafana-container grafana/grafana'
                }
            }
        }

        stage('Prometheus') {
            steps {
                script {
                
                    sh 'docker stop prometheus-container || true'
                    sh 'docker rm prometheus-container || true'

                    sh 'docker run -d -p 9094:9090 --name prometheus-container prom/prometheus'
                }
            }
        }
    }
}
     


