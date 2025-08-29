# Proyecto Demo Ci con Java, Maven, Git y Github Actions
## Objetivo
Este proyecto demuestra como profesionalizar un flujo de **pruebas automatizadas** en Java,
implementando **gestion de versiones con Git**,**configuracion de dependencias con Maven**,
**pruebas unitarias con JUnit 5**, y un **pipeline de Integracion con Github Actions**

## Gestion de dependencias(Maven) y **JUnit 5**
- Dependencia principal de testing:**JUnit 5 (Jupiter)
- Ejecucion local: 'mvn clean test'
- Reporte locales: 'target/surefire-reports'

## Gestion de Versiones
- Ramas:
    - 'main' (principal)
    - 'feature/calculadora-suma' (funcionalidad+test de suma)
    - 'feature/calculadora-resta' (funcionalidad + test de resta)
    - 'ci/github-actions' (pipeline)
- Commits con mensajes claros siguiendo recomendaciones (feat,test,ci,docs,chore)

## Pruebas Unitarias atomicas
- Clase bajo prueba: 'src/main/java/com/empresa/app/Calc.java'
- Tests: 'src/test/java/com/empresa/app/CalcTest.java'
- Casos:
  - 'suma_funciona()'
  - 'resta_funciona()'

## Estructura del proyecto
```
demo-ci/
├─ pom.xml
├─ .gitignore
├─ src/main/java/com/empresa/app/Calc.java
├─ src/test/java/com/empresa/app/CalcTest.java
└─ .github/workflows/ci.yml

```
## Archivo .gitignore
Se incluyo un archivo '.gitignore' adaptado a proyectos Java/Maven e IntelliJ, con las sgtes reglas:
```
target/ ->evita subir archivos compilados
.idea/  -> configuracion local de IntelliJ
*.iml   -> configuracion local de IntelliJ
*.log   -> evita arvhicos de logs temporales

```
## Pipeline de Integracion Continua(Github Actions)

se configuro un workflow en `.github/workflows/ci.yml` que ejecuta automaticamente los tests
en cada `push` o`pull_request`

```
name: Java CI
on: [push, pull_request]

jobs:
  build-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '17'
          cache: maven
      - name: Build & Test
        run: mvn -B clean test
      - name: Publicar reporte Surefire
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: surefire-reports
          path: target/surefire-reports
```
### Que hace :
- Descarga el repo.
- Instala JDK 17
- Ejecuta `mvn clean test`
- 
