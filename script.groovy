def testApp() {
  echo "Testing app ... with $BRANCH_NAME";
}

current_version = "1.0.0"

def deployApp() {
  echo "Deploying app ... with $BRANCH_NAME";
  echo "Current version: $current_version"
  withCredentials([
      sshUserPrivateKey(
          credentialsId: 'ec2-ssh',
          usernameVariable: 'USERNAME',
          keyFileVariable: 'KEY_FILE',
      ),
      string(credentialsId: 'ec2-ip', variable: 'EC2_IP')
  ]) {
    def remote = [:]
    remote.name = USERNAME
    remote.host = EC2_IP
    remote.user = USERNAME
    remote.allowAnyHosts = true
    remote.identityFile = KEY_FILE
    sshCommand remote: remote, command: "echo 'Hello world' >> log.txt"
    sshCommand remote: remote, command: "docker run -p 80:3000 nobitran/node-app:$current_version"
    sshCommand remote: remote, command: "docker ps"
  }
}

def updateVersion(imageName, creId) {
  echo "imageName: $imageName"
  echo "Building app ... with $BRANCH_NAME"
  def matcher = readJSON file: 'package.json'
  def new_version = increaseVersion(matcher.version, 'major')
  matcher.version = new_version;

  writeJSON file: 'package.json', json: matcher.toString(), pretty: 2

  def latest_matcher = readJSON file: 'package.json'
  echo latest_matcher.version;
  def new_name = imageName + ':' + latest_matcher.version
  buildImage(new_name, creId)
  commitVersion(latest_matcher.version)
}

def commitVersion(new_version) {
  current_version = new_version
  withCredentials([
      usernamePassword(
          credentialsId: 'nobitran-github',
          usernameVariable: 'USERNAME',
          passwordVariable: 'PASSWORD'
      )
  ]) {
      sh 'git config user.email "jenkins@gmail.com"'
      sh 'git config user.username "jenkins"'
      sh 'git remote set-url origin https://$USERNAME:$PASSWORD@github.com/nobitran/nodejs-websocket-example.git'
      sh 'git pull'
      sh "git add ."
      sh 'git commit -m "feat: update $new_version in CI"'
      sh "git push origin HEAD:$BRANCH_NAME"
      echo "Pushed image to dockerhub"
  }
}


return this