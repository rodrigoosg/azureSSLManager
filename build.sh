#!/bin/bash

export TERM=${TERM:-dumb}

set -e -x

pushd azureSSLManager
  chmod +x gradlew
  bash gradlew test
popd