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
Actividad 2 – BDD, Reportes y Performance
Sesión Three Amigos (simulada)

PO: define valor de negocio.

QA: criterios de aceptación y ejemplos.

Dev: factibilidad técnica.

Historia de usuario: Como usuario quiero iniciar sesión con credenciales válidas para acceder a mi cuenta.

Criterios de aceptación:

Credenciales correctas ⇒ login exitoso.

Credenciales inválidas ⇒ login fallido.

Mensaje genérico en caso de error.

Escenarios BDD en Gherkin

Archivo: src/test/resources/features/login.feature

Característica: Login de usuario
Antecedentes:
Dado existe un usuario "alice" con contraseña "secret"

Escenario: Login exitoso con credenciales válidas
Cuando intento iniciar sesión con usuario "alice" y contraseña "secret"
Entonces el resultado del inicio de sesión debe ser "exitoso"

Esquema del escenario: Login fallido con credenciales inválidas
Cuando intento iniciar sesión con usuario "<usuario>" y contraseña "<contraseña>"
Entonces el resultado del inicio de sesión debe ser "fallido"

    Ejemplos:
      | usuario | contraseña |
      | pedro   | 1234       |
      | manuel  | perro      |
      | samuel  | gato       |

Step Definitions y Servicio

LoginSteps.java: implementa los pasos de Cucumber.

AuthService.java: servicio simulado que valida credenciales (alice/secret).

Reportes BDD

Archivos generados:

HTML navegable: target/cucumber-report.html

JSON: target/cucumber.json

Pipeline CI (rama act2)
Prueba de Performance (k6)

Script: k6/login_smoke.js

import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
vus: 2,
duration: "10s",
thresholds: {
http_req_failed: ["rate<0.01"],
http_req_duration: ["p(95)<800"],
},
};

export default function () {
const res = http.get("https://test.k6.io/");
check(res, { "status is 200": (r) => r.status === 200 });
sleep(1);
}
El Job Summary en Actions actúa como un mini-dashboard, mostrando:


Conclusiones

El proyecto evolucionó desde pruebas unitarias básicas hasta escenarios BDD integrados en un pipeline de CI/CD, incluyendo reportes navegables y métricas de performance. Esto permite asegurar calidad, trazabilidad y confiabilidad en el desarrollo de software.