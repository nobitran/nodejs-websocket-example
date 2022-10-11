def buildApp() {
  echo "Building app ...";
  sh "npm install"
}

def testApp() {
  echo "Testing app ...";
}

def deployApp() {
  echo "Deploying app ..."
}


return this