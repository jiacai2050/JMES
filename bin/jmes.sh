#!/usr/bin/env bash

bin=`dirname "$0"`
bin=`cd "$bin"; pwd`

conf=`cd $bin/../conf; pwd`
build=`cd $bin/../build; pwd`

class="$build/libs/JMES-0.1-alpha.jar"

java -jar -Dconf="$conf/jmes.properties" $class $@
