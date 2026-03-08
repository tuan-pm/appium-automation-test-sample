pipeline {
    agent any

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

        stage('Android BrowserStack Tests') {
            steps {
                withCredentials([string(credentialsId: 'browserstack-user', variable: 'BS_USER'),
                                string(credentialsId: 'browserstack-key', variable: 'BS_KEY')]) {
                    sh "mvn test -PBandroid -Dbrowserstack.user=${BS_USER} -Dbrowserstack.key=${BS_KEY}"
                }
            }
        }

        stage('iOS BrowserStack Tests') {
            when {
                expression { return params.RUN_IOS_TESTS == 'true' }
            }
            steps {
                withCredentials([string(credentialsId: 'browserstack-user', variable: 'BS_USER'),
                                string(credentialsId: 'browserstack-key', variable: 'BS_KEY')]) {
                    sh "mvn test -PBios -Dbrowserstack.user=${BS_USER} -Dbrowserstack.key=${BS_KEY}"
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
