# EntregaYa

Subsistema de gestión de entregas del sistema distribuido del laboratorio de Sistemas Distribuidos (FPUNA). Maneja pedidos, pagos, clientes, repartidores y seguimiento de entregas.

Forma parte de un sistema compuesto por dos organizaciones autónomas que se comunican por paso de mensajes sobre sockets TCP/UDP. La contraparte de este subsistema es **[SuperMax](https://github.com/riverondev/SuperMax)**.

> Estado actual: bootstrap. El proyecto tiene la configuración base de Maven y la estructura de packages. La clase `fpuna.entregaya.Main` referenciada en `pom.xml` aún no está implementada (es un placeholder).

## Stack

- Java 1.8
- Apache Maven (fat JAR con `maven-shade-plugin`)
- PostgreSQL 12+
- Sockets TCP para servicios transaccionales
- Sockets UDP para actualización de ubicación de repartidores
- JSON via `json-simple 1.1.1`
- Driver JDBC `postgresql 42.7.4`

## Prerrequisitos

- JDK 1.8 instalado y disponible en `PATH`
- Maven 3.6 o superior
- PostgreSQL 12 o superior corriendo en `localhost:5432` (será necesario cuando se defina el esquema de la base)

## Setup

### 1. Clonar el repositorio

```bash
git clone https://github.com/mbarakajap/EntregaYa.git
cd EntregaYa
```

### 2. Compilar y empaquetar

```bash
mvn clean package
```

Esto genera un fat JAR ejecutable en `target/entregaya-1.0-SNAPSHOT.jar` con todas las dependencias incluidas.

### 3. Ejecutar

```bash
java -jar target/entregaya-1.0-SNAPSHOT.jar
```

> La clase `Main` es un placeholder. Cuando la implementes, asegurate de que su FQN coincida con `<mainClass>` en `pom.xml` (actualmente `fpuna.entregaya.Main`).

## Estructura del proyecto

```
EntregaYa/
├── pom.xml                              Configuración de Maven y dependencias
├── src/
│   ├── main/
│   │   ├── java/fpuna/entregaya/        Código fuente (paquete base)
│   │   └── resources/                   Recursos (configs, etc.)
│   └── test/
│       └── java/                        Tests unitarios
└── target/                              Generado por Maven (gitignored)
```

Base package: `fpuna.entregaya`. Toda clase Java debe estar bajo `src/main/java/fpuna/entregaya/` y declarar `package fpuna.entregaya[.subpaquete];`.

## Comunicación distribuida

- **TCP**: servicios transaccionales (registro de pedido, confirmación de pago, etc.). El subsistema expone un servidor TCP al que se conectan los clientes (incluido SuperMax).
- **UDP**: actualización periódica de ubicación de los repartidores.

Los contratos JSON de cada servicio (request/response) están documentados en [`docs/EntregaYa_v3.md`](docs/EntregaYa_v3.md).

## Licencia

Proyecto académico — uso educativo, Facultad Politécnica UNA.
