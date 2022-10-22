def testApp() {
  echo "Testing app ... with $BRANCH_NAME new";
}

def deployApp() {
  echo "Deploying app ... with $BRANCH_NAME new";
}


def updateVersion(imageName, creId) {
  echo "imageName: $imageName"
  echo "Building app ... with $BRANCH_NAME"
  def matcher = readJSON file: 'package.json'
  def new_version = increaseVersion(matcher.version, 'major')
  matcher.version = new_version;

  writeJSON file: 'package.json', json: matcher.toString()

  def latest_matcher = readJSON file: 'package.json'
  echo latest_matcher.version;
  def new_name = imageName + ':' + latest_matcher.version
  buildImage(new_name, creId)
}


return this