FROM cantara/alpine-openjdk-jdk9

COPY ./build/distributions .

CMD "tar -xvf jenkis-build-test-1.0-SNAPSHOT.tar"

ENTRYPOINT "java cp jenkis-build-test-1.0-SNAPSHOT/lib/* jenkis-build-test-1.0-SNAPSHOT/lib/jenkis-build-test-1.0-SNAPSHOT.jar"