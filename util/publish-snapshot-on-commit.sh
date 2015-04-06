# see https://coderwall.com/p/9b_lfq

if [ "$TRAVIS_REPO_SLUG" == "olivierlemasle/java-certificate-authority" ] && \
   [ "$TRAVIS_JDK_VERSION" == "oraclejdk7" ] && \
   [ "$TRAVIS_PULL_REQUEST" == "false" ] && \
   [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Publishing maven snapshot...\n"

  mvn clean source:jar deploy --settings="util/settings.xml" -DskipTests=true -Dmaven.javadoc.skip=true

  echo -e "Published maven snapshot"
fi
