@echo off
set SAVECLASSPATH=%CLASSPATH%

set CLASSPATH=lib/antlr-4.5-complete.jar;

java org.antlr.v4.Tool -visitor -o src/prelogoparsing  grammars/PreLogo.g4
pause

set CLASSPATH=%SAVECLASSPATH%
 