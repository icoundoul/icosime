pipeline {

agent:any
    /*parameters {
        choice(choices: ['int','preprod', 'prod'], description: 'Quel environnement ?', name: 'env')
    }

    tools {
        maven 'maven-3.6.0'
		jdk 'jdk-8'
    }*/

    options {
        disableConcurrentBuilds()
    }
	
	triggers {
        pollSCM 'H/59 * * * *'
    }

    stages {
        stage('Clean') {
            steps {
                deleteDir()
            }
        }

        stage('Clone') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "echo ${params.env}"
                sh "mvn clean install -P${params.env},livraison deploy"
            }
        }
	   
	    stage('Analyse Sonar') {
            steps {
                withSonarQubeEnv('sonar-entreprise') {
                    sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.3.0.603:sonar"
                  }
            }
        }
        
    }
}

