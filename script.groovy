def testApp() {
  echo "Testing app ... with $BRANCH_NAME";
}

def deployApp() {
  echo "Deploying app ... with $BRANCH_NAME";
}

def buildImage(imageName, version, creId) {
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
      sh "docker push $imageName:$version.$BUILD_NUMBER"
      echo "Pushed image to dockerhub"
  }
}


return this