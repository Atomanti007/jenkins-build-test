FROM cantara/alpine-openjdk-jdk9

COPY ./build/distributions .

RUN tar -xvf jenkins-build-test-1.0-SNAPSHOT.tar
RUN chmod 755 -R jenkins-build-test-1.0-SNAPSHOT/

ENTRYPOINT java -cp "jenkins-build-test-1.0-SNAPSHOT/lib/jenkins-build-test-1.0-SNAPSHOT.jar:jenkins-build-test-1.0-SNAPSHOT/lib/*" hu.kzsolt.Server