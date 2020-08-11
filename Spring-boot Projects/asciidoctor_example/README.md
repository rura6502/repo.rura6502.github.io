# Goal

deploy web project as Jar with javadoc and restdoc

# details

if you package the jar using this command

```
gradle bootJar
```

html files of `restdoc` and `javadoc` will be generated in `/src/main/resources/static/docs`.

if you run the jar,

```
gradle bootRun
```

you can access this url to see the javadoc and restdoc

```
javadoc : http://localhost:8080/docs/java/index.html
restdoc : http://localhost:8080/docs/rest/test/test1/http-request.html
```
