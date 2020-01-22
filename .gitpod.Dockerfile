FROM gitpod/workspace-full-vnc

RUN apt-get update \
    && apt-get install -y openjfx libopenjfx-java matchbox \
    && apt-get clean && rm -rf /var/cache/apt/* && rm -rf /var/lib/apt/lists/* && rm -rf /tmp/* \
    && bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh && sdk install java 8.0.202-zulufx && sdk default java 8.0.202-zulufx"
