FROM scratch

CMD "echo $(ls)"
COPY ./build/distributions .

CMD ["/bin/sh"]