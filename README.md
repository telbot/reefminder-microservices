To be Completed.

Start-up Order
1) config-server
2) webservice-registry
3) api-gateway (Need to setup a database and configure)
4) auth-server (Need to setup a database and configure)
5) user-webservice
6) task-webservice
7) comments-webservice

api-gateway

Issues:

cloudVersion = '1.1.0.RC2' needs to updated from release candidate when available.
In the meantime I did this: https://spring.io/blog/2016/03/24/spring-cloud-brixton-rc1-is-now-available.
Should update this to remove these changes when released.

This sounds like it's related to https://issues.gradle.org/browse/GRADLE-2795.

The easiest way to solve this will be to delete everything under C:\Users\Administrator\.gradle\caches. There is a cache.properties.lock that is holding a global lock which is preventing you from running your script.