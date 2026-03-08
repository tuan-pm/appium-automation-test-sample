pipeline {
    agent any

    environment {
        // Use Jenkins Credentials Plugin to store BROWSERSTACK_USER and BROWSERSTACK_KEY
        // These IDs must match the ones you create in Manage Jenkins > Credentials
        BS_USER = credentials('browserstack-user')
        BS_KEY = credentials('browserstack-key')
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
                sh "mvn test -PBandroid -Dbrowserstack.user=${BS_USER} -Dbrowserstack.key=${BS_KEY}"
            }
        }

        stage('iOS BrowserStack Tests') {
            when {
                expression { return params.RUN_IOS_TESTS == 'true' }
            }
            steps {
                sh "mvn test -PBios -Dbrowserstack.user=${BS_USER} -Dbrowserstack.key=${BS_KEY}"
            }
        }
    }

    post {
        always {
            script {
                // If the Allure Jenkins plugin is installed, this will generate and show the report
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
            archiveArtifacts artifacts: 'target/*.jar, log/**', allowEmptyArchive: true
        }
    }
}
