pipeline {
    agent { label 'slave_linux_sisn' }

    parameters {
        choice(choices: ['int','preprod', 'prod'], description: 'Quel environnement ?', name: 'env')
    }

    tools {
        maven 'maven-3.6.0'
		jdk 'jdk-8'
    }

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
        stage('Deploy') {
             when {
                expression { env.GIT_BRANCH == 'master' }
            }
             steps {
                sh "ssh -o StrictHostKeyChecking=no ext-icoundoul@fr000-ppdapp156 rm -rf /firestorm-ci-${params.env}/firestorm-ws/firestorm*"
	     		sh "scp -o StrictHostKeyChecking=no -v -r -p target/firestorm-*.war  ext-icoundoul@fr000-ppdapp156:/firestorm-ci-${params.env}/firestorm-ws/firestorm-ws.war"
	     		sh "ssh -o StrictHostKeyChecking=no ext-icoundoul@fr000-ppdapp156 rm -rf /firestorm-ci-${params.env}/firestorm-ws/properties/*"
	     		sh "scp -o StrictHostKeyChecking=no -v -r -p target/firestorm-*${params.env}*.zip  ext-icoundoul@fr000-ppdapp156:/firestorm-ci-${params.env}/firestorm-ws/properties/"
	     		sh "ssh -o StrictHostKeyChecking=no ext-icoundoul@fr000-ppdapp156 unzip /firestorm-ci-${params.env}/firestorm-ws/properties/firestorm-backend-${params.env}-properties.zip -d /firestorm-ci-${params.env}/firestorm-ws/properties/"

	      }
        }
        
    }
}

