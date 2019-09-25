FROM scratch

COPY ./build/distributions .

CMD ["/bin/sh"]