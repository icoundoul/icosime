node {
	    
	stage('Checkout') {
	    git 'https://github.com/icoundoul/icosime' 
	    }
	    




        stage('Build') {
         
                sh "mvn clean install -Plivraison deploy"
         }
	   
	    /*stage('Analyse Sonar') {
            steps {
                withSonarQubeEnv('sonar-entreprise') {
                    sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.3.0.603:sonar"
                  }
            }
        }*/

}

