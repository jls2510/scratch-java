#!/bin/bash

# signJarFiles function definition
signJarFiles() {
    echo " - Signing jars ..."
    for jarFile in ./*.jar; do
      echo "   - ${jarFile}"
      
      #Remove old signatures
      zip -qd ${jarFile} 'META-INF/*.SF' 'META-INF/*.RSA'
      
      #Add new signature
      jarsigner -keystore ${SCRIPT_DIR}/keystore -storepass WSTHAWTE -keypass WSTHAWTE ${jarFile} WSTHAWTE
    done
    echo " - Done Signing jars ..."
}

# get a reference to the dir where the script lives
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

# do the work
signJarFiles
