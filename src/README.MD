1. Поместить wmq.jmsra.rar file в EAP_HOME/standalone/deployments/ директорию.
1. В консоли

```
EAP_HOME/bin/jboss-cli.bat

connect

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar:add(archive=wmq.jmsra.rar, transaction-support=NoTransaction)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/admin-objects=queue-ao:add(class-name=com.ibm.mq.connector.outbound.MQQueueProxy, jndi-name=java:jboss/MQ.QUEUE.NAME)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/admin-objects=queue-ao/config-properties=baseQueueName:add(value=MQ.QUEUE.NAME)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/admin-objects=queue-ao/config-properties=baseQueueManagerName:add(value=MQ.QUEUE.MANAGER)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd:add(class-name=com.ibm.mq.connector.outbound.ManagedConnectionFactoryImpl, jndi-name=java:jboss/MQ.CONNECTIONFACTORY.NAME, tracking=false)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=hostName:add(value=MQ.HOST.NAME)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=port:add(value=MQ.PORT)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=channel:add(value=MQ.CHANNEL.NAME)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=transportType:add(value=MQ.CLIENT)

/subsystem=resource-adapters/resource-adapter=wmq.jmsra.rar/connection-definitions=mq-cd/config-properties=queueManager:add(value=MQ.QUEUE.MANAGER)

/subsystem=ejb3:write-attribute(name=default-resource-adapter-name,value=wmq.jmsra.rar)

```
3. Собрать war файл. Поместить в EAP_HOME/standalone/deployments 
1. Выполнить в консоли `EAP_HOME/bin/standalone.bat -c standalone-full.xml`
1. Запрос `http://localhost:8080/learnServlet/servlet?name=Ella&surname=Fischenko&account=12345&balance=6000`
1. Для БД:
    1. Поместить в EAP_HOME/standalone/deployments/ директорию JDBC драйвер postgres(jar файл)
    1. Используя [этот источник](https://developer.jboss.org/wiki/JBossAS7-DatasourceConfigurationForPostgresql) сконфигурировать подключение к БД

1. Для автоматического деплоя на jboss `mvn wildfly:redeploy`
1. Полезные ссылки : 
    1. [DEPLOY THE WEBSPHERE MQ RESOURCE ADAPTER](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.0/html/configuring_messaging/resource_adapters) 
    1. [DEPLOY THE WEBSPHERE MQ RESOURCE ADAPTER 6 XML](https://access.redhat.com/documentation/en-us/jboss_enterprise_application_platform/6/html/administration_and_configuration_guide/deploy_the_websphere_mq_resource_adapter)
    1. [GIT HUB](https://github.com/gautric/JBoss-WMQ)
    1. [JBossAS7-DatasourceConfigurationForPostgresql](https://developer.jboss.org/wiki/JBossAS7-DatasourceConfigurationForPostgresql)

