#!/bin/bash

export TERM=${TERM:-dumb}

set -e -x

pushd azureSSLManager
  ./gradlew test
popd