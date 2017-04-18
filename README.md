# EuskalSchema
Versión reducida de Schema en RDFa, para usar en euskadi.eus. Esta versión se obtiene ejecutando un programa que: 

1. Lee un archivo Schema en turtle, cargándolo en un modelo RDF interno.
2. Ejecuta una consulta SPARQL (de tipo CONSTRUCT) sobre ese modelo interno, obteniendo otro modelo que es un subconjunto del original (En la consulta se especifica la entidad "raíz" para extraer el subconjunto). La consulta SPARQL puede se modificada para extraer cualquier entidad.
3. Escribe el nuevo modelo como un documento RDFa. 
 
Para configurar el programa, editar `src/test/resources/configuration/config.yaml` y especificar el archivo con la consulta SPARQL a ejecutar, el archivo origen, el archivo RDFa resultante, y un template para el archivo RDFa:

```
sparql: person.sparql
schema-file: C:\Users\megana\git\EuskalSchema\src\test\resources\result\schema.ttl
html-result-file: C:\Users\megana\git\EuskalSchema\src\test\resources\result\euskal-schema.html
html-template: /src/main/resources/schema_template.ftl
```

Para configurar la apariencia del RDFa resultante, editar el template (en este caso `src/main/resources/schema_template.ftl`).

Para ejecutar el programa ejecutar el test `es.eurohelp.euskalschema.rdfextractor.test.RdfaManagerTest`.



