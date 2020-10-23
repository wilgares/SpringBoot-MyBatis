#!groovy

node {
   // ------------------------------------
   // -- ETAPA: Compilar
   // ------------------------------------
   stage 'Compilar'
   
   // -- Configura variables
   echo 'Configurando variables'
   def mvnHome = tool 'LOCAL-MAVEN'
   env.PATH = "${mvnHome}/bin:${env.PATH}"
   echo "var mvnHome='${mvnHome}'"
   echo "var env.PATH='${env.PATH}'"
   
   // -- Descarga codigo desde SCM
   echo 'Descargando codigo de SCM'
   sh 'rm -rf *'
   checkout scm
   
   // -- Compilando
   echo 'Compilando aplicacion'
   sh 'mvn clean compile'
   
   // ------------------------------------
   // -- ETAPA: Test
   // ------------------------------------
   stage 'Test'
   echo 'Ejecutando tests'

      sh 'mvn verify'
      //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])

   // ------------------------------------
   // -- ETAPA: Code Quality Analysis
   // ------------------------------------
   stage 'Calidad Codigo'
   echo 'enviando codigo a SonarQB'
   withSonarQubeEnv(credentialsId: 'f4753850afcc85637b077951997f0576623f2803', installationName: 'http://sonarqube') { // You can override the credential to be used
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
    }
   
   
    // ------------------------------------
   // -- ETAPA: BUild and Push Image
   // ------------------------------------
   stage 'Push Image'
   echo 'COnstruyendo Imagen y haciendo PUSH'
   
   
   // ------------------------------------
   // -- ETAPA: Instalar
   // ------------------------------------
   stage 'Instalar'
   echo 'Instala el paquete generado en el repositorio maven'
   sh 'mvn install -Dmaven.test.skip=true'
   
   // ------------------------------------
   // -- ETAPA: Archivar
   // ------------------------------------
   stage 'Archivar'
   echo 'Archiva el paquete el paquete generado en Jenkins'
   step([$class: 'ArtifactArchiver', artifacts: '**/target/*.jar, **/target/*.war', fingerprint: true])
}

