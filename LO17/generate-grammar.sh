#!/bin/zsh

export CLASSPATH=/home/romain/git/UTC-GI/LO17/TD/antlr-3.5.2-complete.jar

ORIG=`pwd`
cd /home/romain/git/UTC-GI/LO17/TD/FINAL_PROJET/

rm -rf ../OUR_FILES/output

# Compile grammar
java org.antlr.Tool -o src  "/home/romain/git/UTC-GI/LO17/TD/OUR_FILES/Tal_OURS.g"

# Compile Java files
javac -cp /home/romain/git/UTC-GI/LO17/TD/antlr-3.5.2-complete.jar:src src/Tal*.java -d build/classes
cp -vf /home/romain/git/UTC-GI/LO17/TD/FINAL_PROJET/build/classes/Tal* /home/romain/workspace_eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/FINAL_PROJET/WEB-INF/classes/

# Reloading Tomcat
touch /home/romain/workspace_eclipse/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/FINAL_PROJET/WEB-INF/classes/HomeController.class

cd $ORIG
