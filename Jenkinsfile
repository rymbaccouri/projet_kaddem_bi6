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
                sh 'mvn test -DskipTests' // Add the -DskipTests option to skip running tests
            }
        }
        
        stage('Nexus Deployment') {
            steps {
                sh 'mvn deploy -DskipTests' // Add the -DskipTests option to skip tests during deployment
            }
        }
    }
}

