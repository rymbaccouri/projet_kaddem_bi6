pipeline {
    agent any

    stages {

        stage('git') {
            steps {
                echo 'Pulling... '
                git branch: 'GestionDepartement',
                url: 'https://github.com/rymbaccouri/projet_kaddem_bi6.git'
            }}

        stage('Maven Clean') {
            steps {

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
                                     sh "docker login -u ousse -p 211JMT1384o"
                                     sh "docker tag back:1.0 ousse/back:1.0"
                                     sh "docker push ousse/back:1.0"
                               }
                       }



          	stage('Docker compose') {
                 steps {
                     sh 'docker compose build'
                     sh 'docker compose up -d'
     	    }	}

        
     stage('Configure Grafana') {
                                    steps {
                                        script {
                                            // Exécutez les commandes pour configurer Grafana, par exemple, via l'API REST de Grafana
                                            // Exemple: Créez un tableau de bord via l'API Grafana
                                            sh 'curl -X POST -H "Content-Type: application/json" -d \'{"dashboard": {...}}\' http://192.168.3.17:4004/d/haryan-jenkins/jenkins3a-performance-and-health-overview?orgId=1'
                                        }
                                    }
                             }

              stage('Email') {
               steps {
                           mail bcc: '', body: ''' Successful Completion of Kaddem
                           ''', cc: '', from: '', replyTo: '', subject: 'Jenkins Reponse', to: 'abedelwahed.oussema@esprit.tn'
                        }
                         }


    }}



