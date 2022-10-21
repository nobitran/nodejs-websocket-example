def testApp() {
  echo "Testing app ... with $BRANCH_NAME";
}

def deployApp() {
  echo "Deploying app ... with $BRANCH_NAME";
}

def buildImage(imageName, creId) {
  echo "Building app ... with $BRANCH_NAME"
  withCredentials([
      usernamePassword(
          credentialsId: creId,
          usernameVariable: 'USERNAME',
          passwordVariable: 'PASSWORD'
      )
  ]) {
      sh "docker build -t $imageName ."
      sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
      sh "docker push $imageName"
      echo "Pushed image to dockerhub"
  }
}


return this