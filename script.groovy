def testApp() {
  echo "Testing app ... with $BRANCH_NAME new";
}

def deployApp() {
  echo "Deploying app ... with $BRANCH_NAME new";
}

def getCurrentVersion() {
  def matcher = readJSON file: 'package.json'
  return matcher.version;
}

def buildImageAndUpdateVersion(imageName, version, creId) {
  echo "imageName: $imageName"
  echo "Building app ... with $BRANCH_NAME"
  def matcher = readJSON file: 'package.json'
  matcher.version = version;

  writeJSON file: 'package.json', json: matcher.toString()

  def latest_matcher = readJSON file: 'package.json'
  echo latest_matcher.version;
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