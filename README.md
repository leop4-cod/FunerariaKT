# FunerariaApp - Aplicación Móvil Android

## Descripción

FunerariaApp es una aplicación móvil desarrollada en Kotlin para la plataforma Android, diseñada para la gestión integral de una empresa funeraria. La aplicación consume una API REST desarrollada en Django REST Framework conectada a una base de datos PostgreSQL desplegada en un servidor de producción.

La arquitectura implementada sigue el patrón MVVM (Model-View-ViewModel) con separación de capas de datos, dominio y presentación. La interfaz de usuario está construida completamente con Jetpack Compose. La autenticación se realiza mediante tokens JWT y la app diferencia permisos entre usuarios normales y administradores.

---

## Requisitos de instalación

### Entorno de desarrollo

- Android Studio Hedgehog 2023.1.1 o superior
- JDK 17 o superior (incluido en Android Studio)
- Android SDK API 26 (Android 8.0) o superior
- Conexión a internet para descarga de dependencias Gradle

### Dispositivo o emulador

- Android 8.0 (API 26) o superior
- Emulador AVD con imagen del sistema API 26+
- Conexión a internet activa para consumo de la API

---

## Configuración de la URL base del backend

La URL del backend se configura en el archivo `local.properties` ubicado en la raíz del proyecto:

```properties
API_BASE_URL=https://perez-funeraria.uaeftt-ute.site/api/
```

Esta variable es leída en tiempo de compilación por el `BuildConfig` y usada en el módulo de red `NetworkModule.kt` para configurar la instancia de Retrofit.

Si se desea apuntar al backend local desde un emulador Android, reemplazar por:

```properties
API_BASE_URL=http://10.0.2.2:8000/api/
```

Nota: `10.0.2.2` es la dirección que el emulador Android utiliza para acceder a `localhost` del equipo host.

---

## Usuario y contraseña de prueba

### Administrador (permisos completos: crear, editar, eliminar)

| Campo    | Valor       |
|----------|-------------|
| Usuario  | admin       |
| Password | (credencial configurada en el servidor) |
| Rol      | is_staff = true |

### Usuario estándar (solo lectura)

| Campo    | Valor       |
|----------|-------------|
| Usuario  | usuario     |
| Password | (credencial configurada en el servidor) |
| Rol      | is_staff = false |

El rol se determina a partir del campo `is_staff` decodificado directamente del payload del token JWT mediante la clase `JwtDecoder.kt`.

---

## Capturas de pantalla

Las capturas de pantalla de la aplicación en funcionamiento se encuentran en la carpeta `/screenshots` del repositorio, incluyendo:

- Pantalla de inicio de sesión
- Dashboard principal con estadisticas
- Listado de clientes con busqueda activa
- Formulario de creacion de contrato
- Pantalla de detalle de pago
- Pantalla de perfil de usuario
- Vista de eliminacion con dialogo de confirmacion

---

## Entidades implementadas

La aplicacion implementa CRUD completo para las siguientes 8 entidades del backend:

### 1. Clientes
Representa a los familiares o responsables legales del contrato funerario.

Campos: nombre completo, DNI o cedula, telefono, email, direccion.

Endpoint: `/api/clientes/`

### 2. Difuntos
Registro de las personas fallecidas asociadas a un cliente responsable.

Campos: nombre completo, cliente responsable (FK), fecha de nacimiento, fecha de defuncion, causa de fallecimiento, lugar de velacion.

Endpoint: `/api/difuntos/`

### 3. Productos
Inventario de bienes disponibles para los servicios funerarios, como atauds, urnas, flores y accesorios.

Campos: nombre, categoria, precio, stock disponible, proveedor (FK opcional).

Endpoint: `/api/productos/`

### 4. Servicios
Catalogo de servicios funerarios ofrecidos por la empresa, como cremacion, velacion, traslado, entre otros.

Campos: nombre, descripcion, precio base.

Endpoint: `/api/servicios/`

### 5. Contratos
Documento que formaliza la relacion entre el cliente y la empresa, incluyendo el difunto, el total y el estado de pago.

Campos: cliente (FK), difunto (FK opcional), fecha de emision, total, estado de pago (Pendiente, Pagado, Cancelado).

Endpoint: `/api/contratos/`

### 6. Empleados
Personal de la empresa funeraria vinculado a un usuario del sistema.

Campos: usuario (FK), cargo, turno (Manana, Tarde, Noche).

Endpoint: `/api/empleados/`

### 7. Proveedores
Empresas externas que suministran productos a la funeraria.

Campos: nombre de empresa, RUC o NIT, tipo de producto, nombre de contacto, telefono, email, direccion.

Endpoint: `/api/proveedores/`

### 8. Pagos
Registro de los pagos realizados contra un contrato especifico.

Campos: contrato (FK), monto, metodo de pago (Efectivo, Transferencia, Tarjeta, Cheque), referencia, observaciones.

Endpoint: `/api/pagos/`

---

## Listado de pantallas

La aplicacion cuenta con 27 pantallas o rutas de navegacion:

| Pantalla                  | Ruta de navegacion          |
|---------------------------|-----------------------------|
| Inicio de sesion          | `login`                     |
| Dashboard principal       | `home`                      |
| Perfil de usuario         | `profile`                   |
| Lista de Clientes         | `clientes`                  |
| Detalle de Cliente        | `clientes/{id}`             |
| Formulario de Cliente     | `clientes/form?id={id}`     |
| Lista de Difuntos         | `difuntos`                  |
| Detalle de Difunto        | `difuntos/{id}`             |
| Formulario de Difunto     | `difuntos/form?id={id}`     |
| Lista de Productos        | `productos`                 |
| Detalle de Producto       | `productos/{id}`            |
| Formulario de Producto    | `productos/form?id={id}`    |
| Lista de Servicios        | `servicios`                 |
| Detalle de Servicio       | `servicios/{id}`            |
| Formulario de Servicio    | `servicios/form?id={id}`    |
| Lista de Contratos        | `contratos`                 |
| Detalle de Contrato       | `contratos/{id}`            |
| Formulario de Contrato    | `contratos/form?id={id}`    |
| Lista de Empleados        | `empleados`                 |
| Detalle de Empleado       | `empleados/{id}`            |
| Formulario de Empleado    | `empleados/form?id={id}`    |
| Lista de Proveedores      | `proveedores`               |
| Detalle de Proveedor      | `proveedores/{id}`          |
| Formulario de Proveedor   | `proveedores/form?id={id}`  |
| Lista de Pagos            | `pagos`                     |
| Detalle de Pago           | `pagos/{id}`                |
| Formulario de Pago        | `pagos/form?id={id}`        |

---

## Ejemplos de consumo de la API con token

### Obtener token JWT

```http
POST https://perez-funeraria.uaeftt-ute.site/api/token/
Content-Type: application/json

{
  "username": "admin",
  "password": "Admin1234!"
}
```

Respuesta:

```json
{
  "access": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refresh": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Listar clientes (paginado)

```http
GET https://perez-funeraria.uaeftt-ute.site/api/clientes/?page=1
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Buscar clientes por nombre

```http
GET https://perez-funeraria.uaeftt-ute.site/api/clientes/?search=Juan
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Crear un cliente

```http
POST https://perez-funeraria.uaeftt-ute.site/api/clientes/
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json

{
  "nombre_completo": "Juan Perez",
  "dni_cedula": "1234567890",
  "telefono": "0999999999",
  "email": "juan@correo.com",
  "direccion": "Calle Principal 123"
}
```

### Actualizar un cliente

```http
PUT https://perez-funeraria.uaeftt-ute.site/api/clientes/1/
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json

{
  "nombre_completo": "Juan Perez Actualizado",
  "dni_cedula": "1234567890",
  "telefono": "0988888888",
  "email": "nuevo@correo.com",
  "direccion": "Nueva Direccion 456"
}
```

### Eliminar un cliente (requiere rol Administrador)

```http
DELETE https://perez-funeraria.uaeftt-ute.site/api/clientes/1/
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### Crear un contrato

```http
POST https://perez-funeraria.uaeftt-ute.site/api/contratos/
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json

{
  "cliente": 1,
  "difunto": 1,
  "total": 1500.00,
  "estado_pago": "Pendiente"
}
```

### Registrar un pago

```http
POST https://perez-funeraria.uaeftt-ute.site/api/pagos/
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json

{
  "contrato": 1,
  "monto": 500.00,
  "metodo_pago": "Transferencia",
  "referencia": "TRX-20240101-001"
}
```

---

## Instrucciones para ejecutar la app

### Desde Android Studio

1. Clonar el repositorio:

```bash
git clone https://github.com/leop4-cod/FunerariaKT.git
```

2. Abrir Android Studio y seleccionar `File -> Open`. Navegar hasta la carpeta clonada y abrirla.

3. Esperar a que Android Studio sincronice el proyecto con Gradle. Este proceso descarga las dependencias necesarias y puede tardar varios minutos en la primera ejecucion.

4. Verificar que el archivo `local.properties` en la raiz del proyecto contiene:

```properties
sdk.dir=RUTA_A_TU_SDK_ANDROID
API_BASE_URL=https://perez-funeraria.uaeftt-ute.site/api/
```

5. Crear un emulador Android AVD desde `Device Manager` con API 26 o superior, o conectar un dispositivo fisico con depuracion USB habilitada.

6. Ejecutar la aplicacion con el boton `Run` (Shift+F10).

### Compilar el APK de depuracion

Desde la terminal en la raiz del proyecto:

```bash
./gradlew assembleDebug
```

El APK generado se encuentra en:

```
app/build/outputs/apk/debug/app-debug.apk
```

### Compilar el APK desde Android Studio

```
Build -> Build Bundle(s) / APK(s) -> Build APK(s)
```

---

## Arquitectura del proyecto

```
com.funeraria.app/
├── data/
│   ├── local/              # TokenDataStore - almacenamiento seguro del JWT
│   ├── remote/
│   │   ├── api/            # Interfaces Retrofit (AuthApi, 8 EntityApis)
│   │   ├── dto/            # Data Transfer Objects para serializacion
│   │   └── interceptors/   # AuthInterceptor - inyeccion del token Bearer
│   └── repository/         # Implementaciones de repositorios
├── domain/
│   ├── model/              # Modelos de dominio desacoplados de la API
│   └── repository/         # Interfaces de repositorio (contratos)
├── di/                     # Modulos Hilt (NetworkModule, RepositoryModule)
├── navigation/             # Sealed class Screen con 27 rutas
├── ui/
│   ├── auth/               # LoginScreen
│   ├── home/               # Dashboard con estadisticas
│   ├── entity/             # 24 pantallas CRUD (8 entidades x 3 vistas)
│   ├── profile/            # ProfileScreen con cierre de sesion
│   ├── navigation/         # NavGraph y BottomNavBar
│   ├── components/         # Componentes reutilizables (TextField, Button, etc.)
│   ├── theme/              # Color, Typography, Shape, Theme
│   └── viewmodel/          # AuthViewModel y 8 EntityViewModels
└── util/                   # JwtDecoder para lectura del payload del token
```

## Dependencias principales

| Libreria                        | Version      | Uso                              |
|---------------------------------|--------------|----------------------------------|
| Jetpack Compose BOM             | 2024.10.01   | Framework de UI declarativa      |
| Hilt                            | 2.52         | Inyeccion de dependencias        |
| Retrofit                        | 2.11.0       | Cliente HTTP para la API REST    |
| OkHttp Logging Interceptor      | 4.12.0       | Interceptor de autenticacion     |
| DataStore Preferences           | 1.1.1        | Almacenamiento seguro del token  |
| Navigation Compose              | 2.8.3        | Navegacion entre pantallas       |
| Kotlin Coroutines               | 1.8.1        | Programacion asincrona           |
| Lifecycle ViewModel Compose     | 2.8.6        | Integracion ViewModel con Compose|
