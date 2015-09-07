# Docker

Docker does not run natively on Macs. It requires a VM. See:

https://www.docker.com/toolbox
https://docs.docker.com/installation/mac/

Once installed:



Some useful Docker commands:

    docker ps
    docker port <name>

You can now create a sample app:

    egg new clj-api

In this case, we are using a Clojure API template, but others are
available.
