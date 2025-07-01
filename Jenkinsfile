def projectName = 'mobile-qa-framework'
def projectKey = 'com..channels:mobile-qa-framework'
def webhookUrl = 'https://outlook.office.com/webhook/890acd80-d4e8-4194-8e6c-1d2347d035b3@b24c718e-a35f-4654-b2fa-501c8126cb37/JenkinsCI/b99f80f0bbf14714b0325eec1bc4b5c5/731806d1-3232-44b1-81e0-bd2cf00bc791'

pipeline {
    agent {
		label 'jdeveloper'
	}
    stages {
        stage ('Compile') {
            steps {
               office365ConnectorSend message: "Build Started ${env.JOB_NAME} ${env.BUILD_NUMBER} commited by @${env.CHANGE_AUTHOR} (<${env.BUILD_URL}|Open>)",
                    webhookUrl: "${webhookUrl}", status: "RUNNING"
               sh 'mvn clean compile'
            }
        }
        
        stage ('Code analysis') {
            steps {
               sh 'mvn checkstyle:check pmd:check spotbugs:check'
            }
        }
        
        stage ('SQ Code analysis') {
            steps {
                withSonarQubeEnv('Sonarqube') {
                    script {
                        if (env.BRANCH_NAME == 'master') {
                            sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar -Dsonar.projectKey=${projectKey} -Dsonar.projectName=${projectName}"
                        } else {
                            sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar -Dsonar.projectKey=${projectKey} -Dsonar.projectName=${projectName} -Dsonar.branch.name=${env.BRANCH_NAME}" +
                                (env.CHANGE_TARGET == null ? "" : " -Dsonar.branch.target=${env.CHANGE_TARGET}")
                        }
                    }                  
                }

            }
        }
        

        stage('SQ Quality Gate') {
          steps {
            timeout(time: 5, unit: 'MINUTES') {
              waitForQualityGate abortPipeline: true
            }
          }
        }
	}
	post {
        always {
            deleteDir() /* clean up our workspace */
        }
        success {
            office365ConnectorSend message: "Build Successful ${env.JOB_NAME} ${env.BUILD_NUMBER} commited by @${env.CHANGE_AUTHOR} (<${env.BUILD_URL}|Open>)",
            webhookUrl: "${webhookUrl}", status: "SUCCESS", color: "00FF00"
        }
        failure {
            office365ConnectorSend message: "Build FAILED ${env.JOB_NAME} ${env.BUILD_NUMBER} commited by @${env.CHANGE_AUTHOR} (<${env.BUILD_URL}|Open>)",
            webhookUrl: "${webhookUrl}", status: "FAILED", color: "FF0000"
        }

    }
}

