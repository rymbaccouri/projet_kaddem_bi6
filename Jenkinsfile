pipeline {
    agent any
    environment {
        PROJECT_DIR = '5BI6-G2-Kaddem'
    }
    stages {
        
        stage("GIT") {
            steps {
                script {
                    if (!fileExists(env.PROJECT_DIR)) {
                        sh "git clone -b gestionContracts https://elemejri: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@github.com/rymbaccouri/projet_kaddem_bi6.git ${env.PROJECT_DIR}"
                    } else {
                        echo "Le répertoire '${env.PROJECT_DIR}' existe déjà. Mise à jour en cours..."
                        dir(env.PROJECT_DIR) {
                            sh "git checkout gestionContracts"
                            sh "git pull origin gestionContracts"
                            sh 'mvn clean compile'
                        }
                    }
                }
            }
        }

        stage('Clean Project with Maven') {
            steps {
                dir(env.PROJECT_DIR) {
                    sh 'mvn clean'
                }
            }
        }

        stage('Compile Project with Maven') {
            steps {
                dir(env.PROJECT_DIR) {
                    sh 'mvn compile'
                }
            }
        }

        stage('MVN SONARQUBE Analysis') {
            steps {
        // Étape pour compiler le projet avec Maven
                script {
                    dir(env.PROJECT_DIR) {
                        sh 'mvn clean package sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar -Dmaven.test.skip=true'
                    }
        }
    }
        }
        stage('JUNIT-MOCKITO Tests'){
            steps{
                dir(env.PROJECT_DIR){
                echo'laching units test ...'
                sh 'mvn test'                
                }
            }
            post {
                always {
                    dir(env.PROJECT_DIR) {
                        junit '**/target/surefire-reports/*.xml'
                    }
                }
            }
        }

           stage('Nexus') {
            steps {
                dir(env.PROJECT_DIR) {
                    script{
                    artifactPath = "target/kaddem-0.0.1.jar";

                    nexusArtifactUploader(
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            nexusUrl: 'localhost:8081',
                            groupId: 'tn.esprit',
                            version: '1.0',
                            repository: 'maven-releases',
                            credentialsId: 'nexus-credentials',
                            artifacts: [
                                    [artifactId: 'kaddem',
                                     classifier: '',
                                     file      : artifactPath,
                                     type      : 'jar']
                            ]
                    );
                    }
                }
            }
           }

        stage('Build Docker Image') {
            steps {
                dir(env.PROJECT_DIR) {
                echo 'Building the Docker image...'
                        withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                        sh "echo $PWD | docker login -u $USER --password-stdin"
                        sh "docker build -t elemejri/kaddem:1.0 ."
                        }
                }
            }
        }

        stage('Upload to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PWD')]) {
                                        sh "echo $PWD | docker login -u $USER --password-stdin"
                                        sh "docker push elemejri/kaddem:1.0"
                }
            }
        }

        stage('Run Docker Compose') {
            steps {
                echo 'Running Docker Compose...'
                sh 'docker compose -f /var/lib/jenkins/workspace/examen_devops/5BI6-G5-Kaddem/docker-compose.yml up -d'
            }
        }


  stage('Check and Start Prometheus') {
            steps {
                script {
                    def containerRunning = sh(script: "docker ps --format '{{.Names}}' | grep prometheus", returnStatus: true)

                    if (containerRunning != 0) {
                        echo "Container does not exist. Starting Prometheus container..."
                        sh "docker run -d -p 9090:9090 --name prometheus prom/prometheus"
                    } else {
                        echo "Container already exists. Skipping container creation."
                        sh "docker start prometheus"
                    }
                }
            }
        }
    


  stage('Check and Start Grafana') {
            steps {
                script {
                    def containerRunning = sh(script: "docker ps --format '{{.Names}}' | grep grafana", returnStatus: true)

                    if (containerRunning != 0) {
                        echo "Container does not exist. Starting Grafana container..."
                        sh "docker run -d -p 3000:3000 --name grafana grafana/grafana"
                    } else {
                        echo "Container already exists. Skipping container creation."
                        sh "docker start grafana"
                    }
                }
            }
        }
    

        stage('Email Notification') {
            steps {
                script {
                    mail bcc: '', body: '''Welcome to jenkins email alerts.
Thanks,''', cc: '', from: '', replyTo: '', subject: 'Email Notification', to: 'mejri.ele@esprit.tn'
                }
            }
        }
        
        
              }
}


    
