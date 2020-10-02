pipeline {
    agent any 
    stages {
        stage('Example Build') {
            steps {
               echo 'building the applicaction....'
                sh 'mvn -B clean verify'
            }
        }
       stage('Example Test') {
            steps {
               echo 'Testing the applicaction....'
    
            }
        }
       stage('Example Deploy') {
            steps {
               echo 'Deploy the applicaction....'

            }
        }
    }
}
