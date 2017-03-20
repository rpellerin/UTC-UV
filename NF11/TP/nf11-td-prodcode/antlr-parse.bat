@echo off
set SAVECLASSPATH=%CLASSPATH%

set CLASSPATH=lib/antlr-4.5-complete.jar;./bin

java org.antlr.v4.runtime.misc.TestRig prelogoparsing.PreLogo prestat -gui programs/prelogo.txt
pause

set CLASSPATH=%SAVECLASSPATH%
