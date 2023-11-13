pipeline {
    agent any

    stages {
        stage('git') {
            steps {
                echo 'Pulling... '
                git branch: 'gestionequipe',
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

            sh "mvn sonar:sonar -Dsonar.login=squ_3caad03e9a9f520fea0d9f66152f004c2cf99ad8"
        }
    }
}
        stage('JUNIT-MOCKITO'){
            steps{
                echo'laching units test ...'
                sh 'mvn test'
            }
             post {
                  always {
                         junit '**/target/surefire-reports/TEST*.xml'
                            }
                        }
        }
stage('Nexus Deployment') {
    steps {
        sh 'mvn deploy'
    }
}

   stage('Docker Login') {
               steps {
   				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u="mahasmaali" -p="10mars2000" '
   			}
   		}

stage('Build docker image'){
               steps{
                   script{
                       sh 'docker build -t mahasmaali/kaddem:1.0.0 .'
                   }
               }
           }

   	 stage('Push DockerHub') {
                steps {
   		    sh 'docker push mahasmaali/kaddem:1.0.0 '
   			}
   	    post {
   		always {
   			sh 'docker logout'
   		}
           	}
     }
          	stage('Docker compose') {
                 steps {

                     sh 'docker-compose up -d'
     	    }	}


     	     stage('Configure Grafana') {
                                    steps {
                                        script {
                                            // Exécutez les commandes pour configurer Grafana, par exemple, via l'API REST de Grafana
                                            // Exemple: Créez un tableau de bord via l'API Grafana
                                            sh 'curl -X POST -H "Content-Type: application/json" -d \'{"dashboard": {...}}\' http://192.168.3.17:3000/d/haryan-jenkins/jenkins3a-performance-and-health-overview?orgId=1'
                                        }
                                    }
                             }
    }

    post {
                                   success {
                                        mail to: "maha.smaali@esprit.tn",
                                        subject: "success",
                                        body: "success on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}"
                                   }
                                   failure {
                                       mail to: "maha.smaali@esprit.tn",
                                        subject: "Failure",
                                        body: "Failure on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL} "
                                   }


                   }

}
