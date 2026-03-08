pipeline {
    agent any

    parameters {
        choice(name: 'PLATFORM', choices: ['ANDROID', 'IOS', 'API'], description: 'Select the platform to test')
    }

    tools{
         maven 'maven'
     }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Install') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def bsCredentials = [string(credentialsId: 'browserstack-user', variable: 'BS_USER'),
                                        string(credentialsId: 'browserstack-key', variable: 'BS_KEY')]
                    
                    if (params.PLATFORM == 'ANDROID') {
                        echo "Running Android Tests..."
                        withCredentials(bsCredentials) {
                            sh "mvn clean test -PBandroid -Dbrowserstack.user=${BS_USER} -Dbrowserstack.key=${BS_KEY}"
                        }
                    } else if (params.PLATFORM == 'IOS') {
                        echo "Running iOS Tests..."
                        withCredentials(bsCredentials) {
                            sh "mvn clean test -PBios -Dbrowserstack.user=${BS_USER} -Dbrowserstack.key=${BS_KEY}"
                        }
                    } else if (params.PLATFORM == 'API') {
                        echo "Running API Tests..."
                        sh "mvn clean test -Papi"
                    }
                }
            }
        }

        stage('reports') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar, log/**', allowEmptyArchive: true
        }
    }
}
