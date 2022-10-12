def buildApp() {
  echo "Building app ... with $BRANCH_NAME";
  withCredentials([
      usernamePassword(
          credentialsId: 'dockerhub', 
          usernameVariable: 'USERNAME', 
          passwordVariable: 'PASSWORD'
      )
  ]) {
      sh 'docker build -t nobitran/node-app:1.1 .'
      sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
      sh 'docker push nobitran/node-app:1.1'
  }
  
}

def testApp() {
  echo "Testing app ... with $BRANCH_NAME";
}

def deployApp() {
  echo "Deploying app ... with $BRANCH_NAME";
}


return this