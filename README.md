# EV2 – CI con Maven, JUnit y GitHub Actions - Matías CORTÉS ROMERO

Proyecto  para la evaluación de la unidad 2. Implementa pruebas unitarias con JUnit 5 y un pipeline de Integración Continua con GitHub Actions que compila y ejecuta los tests en cada push y pull request. Se adjuntan evidencias de ejecución local y en CI.

---

## 1) Objetivos

- Inicializar un repositorio Git con rama principal y ramas de feature, realizando commits frecuentes y PRs.
- Configurar un proyecto Maven con dependencias de JUnit 5.
- Implementar dos pruebas unitarias atómicas e independientes (suma y resta).
- Organizar el proyecto con la estructura estándar Maven.
- Crear un .gitignore adecuado.
- Configurar un pipeline CI que compile y ejecute los tests en cada push o pull_request.
- Publicar el reporte de tests como artefacto descargable.
- Documentar todo el proceso con comandos y capturas.

---

## 2) Requisitos usados

- Java 17  
- Maven 3.9+  
- Git  
- GitHub (Actions habilitado)

---

## 3) Estructura del proyecto

ev2-ci/
├─ .github/
│ └─ workflows/
│ └─ ci.yml    (Pipeline de GitHub Actions)
├─ src/
│ ├─ main/java/com/macortes/App.java
│ └─ test/java/com/macortes/
│ ├─ AppTest.java
│ ├─ SumaTest.java
│ └─ RestaTest.java
├─ .gitignore
├─ pom.xml   (Configuración de Maven + dependencias)
└─ README.md


- Archivos clave:
- pom.xml: configuración Maven + JUnit 5 (Surefire).
- .gitignore: ignora /target/, IDEs y temporales.
- .github/workflows/ci.yml: pipeline CI.
- SumaTest.java y RestaTest.java: pruebas unitarias atómicas.
- AppTest.java: test base generado por el arquetipo.


- archivo gitignore: 
```bash
/target/
/.idea/
/.vscode/
*.iml
.DS_Store
Thumbs.db

```
---

## 4) Comandos usados

```bash
# Inicialización y primer commit
git init
git add .
git commit -m "chore: estructura inicial (Maven + JUnit + .gitignore)"
git branch -M main
git remote add origin https://github.com/macortesro/ev2-ci.git
git push -u origin main

# Crear rama de feature y abrir PR
git checkout -b feature/ajuste-test

# editar SumaTest (cambio mínimo que se realizó para test) 
git add src/test/java/com/macortes/SumaTest.java
git commit -m "test: ajuste en nombre al interior de SumaTest"
git push -u origin feature/ajuste-test

# Ejecutar los tests localmente
mvn -B -U -ntp clean test
```
---

## 5) Pipeline CI (Github Actions)
El workflow compila y ejecuta tests en cada push y pull_request. Además, publica el reporte Surefire como artefacto (junit-reports).

```bash

name: CI
on: [push, pull_request]

jobs:
  build-and-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'
          cache: maven

      - name: Build & Test
        run: mvn -B -U -ntp clean test

      - name: Publicar reportes JUnit
        uses: actions/upload-artifact@v4
        with:
          name: junit-reports
          path: target/surefire-reports/*
```

## 6) Pruebas unitarias (atómicas e independientes)

src/test/java/com/macortes/SumaTest.java

```bash
package com.macortes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SumaTest {
  @Test
  void suma_dos_enteros() {
    assertEquals(7, 3 + 4);
  }
}

```

src/test/java/com/macortes/RestaTest.java

```bash
package com.macortes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RestaTest {
  @Test
  void resta_dos_enteros() {
    assertEquals(1, 5 - 4);
  }
}
```

## 7) Buenas prácticas aplicadas

Mensajes de commit: chore:, test:, ci:.

Ramas de feature + Pull Requests con checks verdes antes del merge.

.gitignore para evitar binarios (/target/), archivos de IDE y temporales.

Pipeline CI rápido, determinista y con cache de Maven.

## 8) Evidencias (capturas de pantalla del proceso)


- Inicialización de repositorio y primeros commits
![Evidencia 1](docs/img/capturadepantalla%20(1).png)  
![Evidencia 2](docs/img/capturadepantalla%20(2).png)  
![Evidencia 3](docs/img/capturadepantalla%20(3).png)  
![Evidencia 4](docs/img/capturadepantalla%20(4).png)  
![Evidencia 5](docs/img/capturadepantalla%20(5).png)  
![Evidencia 6](docs/img/capturadepantalla%20(6).png)  
![Evidencia 7](docs/img/capturadepantalla%20(7).png)  
![Evidencia 8](docs/img/capturadepantalla%20(8).png)  
![Evidencia 9](docs/img/capturadepantalla%20(9).png)  
![Evidencia 10](docs/img/capturadepantalla%20(10).png)  

---

-  Configuración Maven y pruebas unitarias (local)
![Evidencia 11](docs/img/capturadepantalla%20(11).png)  
![Evidencia 12](docs/img/capturadepantalla%20(12).png)  
![Evidencia 13](docs/img/capturadepantalla%20(13).png)  
![Evidencia 14](docs/img/capturadepantalla%20(14).png)  
![Evidencia 15](docs/img/capturadepantalla%20(15).png)  
![Evidencia 16](docs/img/capturadepantalla%20(16).png)  
![Evidencia 17](docs/img/capturadepantalla%20(17).png)  
![Evidencia 18](docs/img/capturadepantalla%20(18).png)  
![Evidencia 19](docs/img/capturadepantalla%20(19).png)  
![Evidencia 20](docs/img/capturadepantalla%20(20).png)  

---

-  Ejecución en CI/CD con GitHub Actions
![Evidencia 21](docs/img/capturadepantalla%20(21).png)  
![Evidencia 22](docs/img/capturadepantalla%20(22).png)  
![Evidencia 23](docs/img/capturadepantalla%20(23).png)  
![Evidencia 24](docs/img/capturadepantalla%20(24).png)  
![Evidencia 25](docs/img/capturadepantalla%20(25).png)  
![Evidencia 26](docs/img/capturadepantalla%20(26).png)  
![Evidencia 27](docs/img/capturadepantalla%20(27).png)  
![Evidencia 28](docs/img/capturadepantalla%20(28).png)  
![Evidencia 29](docs/img/capturadepantalla%20(29).png)  
![Evidencia 30](docs/img/capturadepantalla%20(30).png)  

---

- Pull Request y Merge a main
![Evidencia 31](docs/img/capturadepantalla%20(31).png)  
![Evidencia 32](docs/img/capturadepantalla%20(32).png)  
![Evidencia 33](docs/img/capturadepantalla%20(33).png)  
![Evidencia 34](docs/img/capturadepantalla%20(34).png)  



## 9) Cómo reproducir

```bash
# para clonar: 
git clone https://github.com/macortesro/ev2-ci.git
cd ev2-ci

# para ejecutar tests localmente
mvn -B -U -ntp clean test

# y para ver reportes locales
ls target/surefire-reports/
```

## 10) Alumno y link a github

Matías CORTÉS ROMERO - Github: https://github.com/macortesro/ev2-ci



