def buildApp() {
  echo "Building app ...";
  sh "npm install"
}

def testApp() {
  echo "Testing app ...";
}

def deployApp() {
  echo "Deploying version ${params.VERSION}"
  echo "Deploying with role $ROLE"
}


return this