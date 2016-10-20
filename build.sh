#!/bin/bash

export TERM=${TERM:-dumb}

set -e -x

pushd azureSSLManager
  bash gradlew test
popd