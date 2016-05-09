To be Completed.

Start-up Order
1) config-server
2) webservice-registry
3) auth-server
4) user-webservice

api-gateway

Issues:

This sounds like it's related to https://issues.gradle.org/browse/GRADLE-2795.

The easiest way to solve this will be to delete everything under C:\Users\Administrator\.gradle\caches. There is a cache.properties.lock that is holding a global lock which is preventing you from running your script.