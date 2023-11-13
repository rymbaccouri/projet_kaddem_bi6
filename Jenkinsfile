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
                    
                    sh 'docker info'

               
                    sh 'ls -la'

                    sh 'cat Dockerfile'
                    sh 'docker build -t test-image .'

               
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


       stage('Grafana') {
            steps {
                script {
                    def grafanaContainerName = 'grafana-container'
                    
                   
                    def existingContainerId = sh(script: "docker ps -q -f name=${grafanaContainerName}", returnStdout: true).trim()

                    
                    if (existingContainerId) {
                        sh "docker stop ${grafanaContainerName}"
                        sh "docker rm ${grafanaContainerName}"
                    }

                   
                    sh 'docker run -d -p 4004:3000 --name grafana-container-new grafana/grafana'
                }
            }}
      stage('Prometheus') {
            steps {
                script {
                    def prometheusContainerName = 'prometheus-container'
                    
                  
                    def existingContainerId = sh(script: "docker ps -q -f name=${prometheusContainerName}", returnStdout: true).trim()

                    
                    if (existingContainerId) {
                        sh "docker stop ${prometheusContainerName}"
                        sh "docker rm ${prometheusContainerName}"
                    }

                    sh 'docker run -d -p 9095:9090 --name prometheus-container prom/prometheus'
                }
            }
        }
        stage('Email Notification') {
    steps {
        emailext body: 'Welcome to Jenkins email alerts.\nThanks,',
                 subject: 'Email Notification',
                 to: 'rym.baccouri@esprit.tn'
    }
}

                

    }
}
     


