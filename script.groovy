def buildApp() {
  echo "Building app ...";
  sh "npm install"
}

def testApp() {
  echo "Testing app ...";
}

def deployApp() {
  sh "systemctl start docker"
  sh "systemctl enable docker"
  sh "usermod -aG docker jenkins"
  echo "Deploying app ..."
}


return this