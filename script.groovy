def testApp() {
  echo "Testing app ... with $BRANCH_NAME";
}

def deployApp() {
  echo "Deploying app ... with $BRANCH_NAME";
}

def buildImage(imageName, version, creId) {
  echo "Building app ... with $BRANCH_NAME"
  def version_name = "$imageName:$version.$BUILD_NUMBER"
  echo "Version: $version_name"
  def matcher = readJSON file: 'package.json'
  echo matcher;
  echo matcher['version'];
  // withCredentials([
  //     usernamePassword(
  //         credentialsId: creId,
  //         usernameVariable: 'USERNAME',
  //         passwordVariable: 'PASSWORD'
  //     )
  // ]) {
  //     sh "docker build -t $imageName ."
  //     sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin"
  //     sh "docker push"
  //     echo "Pushed image to dockerhub"
  // }
}


return this