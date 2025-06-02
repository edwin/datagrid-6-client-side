# Doing a Simple Insert and Query with Red Hat Data Grid 6

## Version
- Red Hat Data Grid 6.5.1
- Red Hat OpenJDK 8

## Cache Config
```xml
<replicated-cache name="GEN_MD_ACCOUNT" mode="SYNC" remote-timeout="300000" start="EAGER" statistics="true">
    <locking isolation="READ_COMMITTED" striping="false" acquire-timeout="3000000" concurrency-level="25000"/>
    <transaction mode="NONE"/>
    <state-transfer timeout="600000"/>
    <indexing index="ALL">
        <property name="default.directory_provider">
            ram
        </property>
        <property name="default.indexmanager">
            near-real-time
        </property>
        <property name="default.worker.execution">
            async
        </property>
        <property name="default.worker.thread_pool.size">
            16
        </property>
    </indexing>
</replicated-cache>
```

## JBoss EAP Config
```
JAVA_OPTS: -XX:+TieredCompilation -Dprogram.name=clustered.bat -Xms4096m -Xmx4096m 
-XX:MaxPermSize=512m -Dsun.rmi.dgc.client.gcInterval=3600000 
-Dsun.rmi.dgc.server.gcInterval=3600000 -Djava.net.preferIPv4Stack=true 
-Dorg.jboss.resolver.warning=true -Djboss.modules.system.pkgs=org.jboss.byteman 
-Djboss.server.default.config=clustered.xml
```

## Duration
```
start putting data
finish putting data for 123935

start querying data
finish querying data for 16663
```