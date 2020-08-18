# Goal

deploy web project as Jar with javadoc and restdoc

# details

if you package the jar using this command

```
gradle bootJar
```

`javadoc`'s documents and `asciidoc`'s documents will be generated in `build/docs/` and bootJar plugin will move this files to `/static` folder in jar files.

you can make template for asciidocs in `src/restdoc/snippets/`



if you run the jar,

```
gradle bootRun
```

you can access this url to see the javadoc and restdoc

```
javadoc : http://localhost:8080/docs/java/index.html
restdoc : http://localhost:8080/docs/rest/index.html
```
