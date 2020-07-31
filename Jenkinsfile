node {
	    
	stage('Checkout') {
	
	    git 'https://github.com/icoundoul/icosime' 
	}
        stage('Build') {
           def mvnhome = tool name: 'maven-3.6.3', type: 'maven'
	   bat "${mvnhome}\\bin\\mvn clean install -Plivraison deploy"
         }
	    /*stage('Analyse Sonar') {
            steps {
                withSonarQubeEnv('sonar-entreprise') {
                    sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.3.0.603:sonar"
                  }
            }
        }*/

}

