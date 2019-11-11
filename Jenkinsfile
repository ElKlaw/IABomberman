pipeline {
    agent any
    triggers {
        pollSCM('*/1 * * * *')
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        disableConcurrentBuilds()
    }

    stages {
        stage('Build') {
            agent {
                docker {
                    image "maven:3.5-jdk-8"
                    args '-e HOME=${userHome}'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean'
                sh 'mvn install -DskipTests'
            }
        }

        stage('Analyse Sonarqube') {
            agent {
                docker {
                    image "maven:3.5-jdk-8"
                    reuseNode true
                }
            }
            steps {
                withSonarQubeEnv('Sonarqube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Build and push Docker image') {
            steps {
                script {
                    docker.withRegistry('https://hub-docker.norsys.fr', 'portus.norsys.fr') {
                        def customImage = docker.build("hub-docker.norsys.fr/ecole_du_dev/cia")
                        customImage.push('latest')
                    }
                }

            }
        }
         stage('Restart containers') {
            agent {
                docker {
                    image 'hub-docker.norsys.fr/jenkins/slave-fleet:latest'
                    args '-u root'
                }
            }

            steps {
                sh """
                eval \$(ssh-agent -s)
                ssh-add /home/jenkins/.ssh/id_rsa
                export PATH=\$PATH:/home/jenkins/fleet/bin

                fleetctl --strict-host-key-checking=false --tunnel=172.20.19.148 stop Pirates_EcoleDuDev_667.service
                sleep 30
                fleetctl --strict-host-key-checking=false --tunnel=172.20.19.148 start Pirates_EcoleDuDev_667.service
                """
            }
        }

    }
}
