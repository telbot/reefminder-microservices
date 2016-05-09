To be Completed.

Issues:

This sounds like it's related to https://issues.gradle.org/browse/GRADLE-2795.

The easiest way to solve this will be to delete everything under C:\Users\Administrator\.gradle\caches. There is a cache.properties.lock that is holding a global lock which is preventing you from running your script.