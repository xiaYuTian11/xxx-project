#!/bin/bash
nohup java -jar -Xms1024m -Xmx2048m -Dloader.path=lib,config xxx-1.0-dev.jar  >/dev/null 2>&1  &