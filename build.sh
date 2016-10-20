#!/bin/bash

export TERM=${TERM:-dumb}

set -e -x

pushd azureSSLManager
  chmod +x gradlew gradle/wrapper/*
  bash gradlew test
popd