# graalVMTest


~~Рабочий вариант.~~
~~После команды `mvn -Pnative native:compile` создается бинарник. При его запуске поднимается http сервер.~~

**Добавим в проект кеш.** 
Раскомментируем `@Bean` в config.CacheConfig.java и  
`@CacheConfig(cacheNames = CACHE_NAME), @CachePut(key = "#user.getId()") `в service.UserServiceImpl.java

Запустим
`mvn clean`
`mvn -Pnative native:compile`

При запуске бинарника
`./target/graalVMTest`
получаем
`org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'caffeineCacheManager': Instantiation of supplied bean failed
...
java.lang.ClassNotFoundException: com.github.benmanes.caffeine.cache.SSSMSA`

Запустим команду
`java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar ./target/graalVMTest-3.3.4.jar`
Запустится jar файл с сервером. Сделаем тестовый http запрос из файла user.http и завершим сервер Ctrl+C

Появилась папка с файлами конфигурации `src/main/resources/META-INF/`

Повторим действия для перекомпилирования бинарника
`mvn clean`
`mvn -Pnative native:compile`

При запуске бинарника
`./target/graalVMTest`
получаем

`org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory': org.hibernate.bytecode.spi.BytecodeProvider: org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl Unable to get public no-arg constructor
...
Caused by: java.lang.NoSuchMethodException: org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl.<init>()
`