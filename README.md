#  FunerariaApp — Aplicación Móvil Android

Aplicación móvil desarrollada en **Kotlin con Jetpack Compose** que consume el backend **FunerariaApi** (Django REST Framework + PostgreSQL). Implementa autenticación JWT, CRUD completo para 8 entidades, búsqueda, paginación y control de permisos por rol.

---

##  Requisitos de instalación

- Android Studio **Hedgehog 2023.1.1** o superior
- JDK 17+ (incluido en Android Studio)
- Android SDK **API 26+** (Android 8.0 Oreo)
- Emulador o dispositivo físico Android
- Backend **FunerariaApi** corriendo (ver sección de backend)

---

##  Configuración de la URL base del backend

La URL se configura en `local.properties` (en la raíz del proyecto):

```properties
API_BASE_URL=http://10.0.2.2:8000/api/
```

> `10.0.2.2` es la dirección que el emulador Android usa para acceder a `localhost` de tu PC.  
> Si usas un dispositivo físico, reemplaza con la IP local de tu PC: `http://192.168.x.x:8000/api/`

---

##  Usuario y contraseña de prueba

Crea un superusuario en el backend:

```bash
cd D:\FunerariaApi
.venv\Scripts\python.exe manage.py createsuperuser
```


Para un usuario normal (sin permisos de escritura):
```bash
python manage.py shell -c "from django.contrib.auth.models import User; User.objects.create_user('usuario', password='user123')"
```

---

##  Instrucciones para ejecutar la app

### 1. Levantar el backend
```bash
cd D:\FunerariaApi
.venv\Scripts\python.exe manage.py runserver
```

### 2. Abrir el proyecto en Android Studio
```
File → Open → D:\proyectito
```

### 3. Sincronizar Gradle
Android Studio sincronizará automáticamente. Espera a que termine (barra inferior).

### 4. Correr la app
- Conecta un emulador o dispositivo
- Presiona el botón **▶ Run** (Shift+F10)

---

##  Entidades

### 1. Clientes (`/api/clientes/`)
Familiares o responsables del contrato funerario.
- **Campos**: Nombre completo, DNI/Cédula, Teléfono, Email, Dirección
- **Búsqueda**: nombre, DNI, email
- **Pantallas**: Lista, Detalle, Formulario

### 2. Difuntos (`/api/difuntos/`)
Registro de personas fallecidas.
- **Campos**: Nombre, Cliente responsable, Fecha nacimiento, Fecha defunción, Causa, Lugar de velación
- **Búsqueda**: nombre, causa de fallecimiento
- **Pantallas**: Lista, Detalle, Formulario

### 3.Productos (`/api/productos/`)
Inventario de ataúdes, urnas, flores y accesorios.
- **Campos**: Nombre, Categoría, Precio, Stock, Proveedor
- **Búsqueda**: nombre, categoría
- **Pantallas**: Lista, Detalle, Formulario

### 4.Servicios (`/api/servicios/`)
Servicios funerarios como cremación, velación, etc.
- **Campos**: Nombre, Descripción, Precio base
- **Búsqueda**: nombre, descripción
- **Pantallas**: Lista, Detalle, Formulario

### 5. Contratos (`/api/contratos/`)
Facturación y planes contratados por los clientes.
- **Campos**: Cliente, Difunto, Total, Estado de pago (Pendiente/Pagado/Cancelado)
- **Búsqueda**: estado de pago
- **Pantallas**: Lista, Detalle, Formulario

### 6. Empleados (`/api/empleados/`)
Personal de la funeraria (solo Admin puede gestionar).
- **Campos**: Usuario, Cargo, Turno (Mañana/Tarde/Noche)
- **Búsqueda**: cargo, turno
- **Pantallas**: Lista, Detalle, Formulario

### 7. Proveedores (`/api/proveedores/`)
Empresas proveedoras de ataúdes, flores, etc.
- **Campos**: Empresa, RUC/NIT, Tipo producto, Contacto, Teléfono, Email, Dirección
- **Búsqueda**: nombre empresa, RUC, contacto
- **Pantallas**: Lista, Detalle, Formulario

### 8. Pagos (`/api/pagos/`)
Registro de pagos asociados a contratos.
- **Campos**: Contrato, Monto, Método de pago, Referencia, Observaciones
- **Búsqueda**: referencia, método de pago
- **Pantallas**: Lista, Detalle, Formulario

---

## Listado de pantallas

| Pantalla | Ruta | Descripción |
|----------|------|-------------|
| Login | `login` | Autenticación JWT |
| Inicio | `home` | Dashboard con estadísticas y accesos rápidos |
| Perfil | `profile` | Info del usuario y cierre de sesión |
| Clientes - Lista | `clientes` | Listado con búsqueda y paginación |
| Clientes - Detalle | `clientes/{id}` | Ver todos los campos |
| Clientes - Formulario | `clientes/form?id={id}` | Crear o editar |
| Difuntos - Lista/Detalle/Form | `difuntos/...` | CRUD completo |
| Productos - Lista/Detalle/Form | `productos/...` | CRUD completo |
| Servicios - Lista/Detalle/Form | `servicios/...` | CRUD completo |
| Contratos - Lista/Detalle/Form | `contratos/...` | CRUD completo |
| Empleados - Lista/Detalle/Form | `empleados/...` | CRUD completo (Admin) |
| Proveedores - Lista/Detalle/Form | `proveedores/...` | CRUD completo |
| Pagos - Lista/Detalle/Form | `pagos/...` | CRUD completo |


---

## Ejemplos de consumo de la API con token

### Obtener token JWT
```bash
curl -X POST http://localhost:8000/api/token/ \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'
```
Respuesta:
```json
{
  "access": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...",
  "refresh": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9..."
}
```

### Listar clientes (con token)
```bash
curl http://localhost:8000/api/clientes/ \
  -H "Authorization: Bearer eyJ0eXAiOiJKV1Qi..."
```

### Crear cliente
```bash
curl -X POST http://localhost:8000/api/clientes/ \
  -H "Authorization: Bearer eyJ0eXAiOiJKV1Qi..." \
  -H "Content-Type: application/json" \
  -d '{
    "nombre_completo": "Juan Pérez",
    "dni_cedula": "1234567890",
    "telefono": "0999999999",
    "email": "juan@example.com"
  }'
```

### Buscar con paginación
```bash
curl "http://localhost:8000/api/clientes/?search=Juan&page=1" \
  -H "Authorization: Bearer eyJ0eXAiOiJKV1Qi..."
```

### Eliminar registro (solo Admin)
```bash
curl -X DELETE http://localhost:8000/api/clientes/1/ \
  -H "Authorization: Bearer eyJ0eXAiOiJKV1Qi..."
```

---

##  Arquitectura del proyecto

```
MVVM + Clean Architecture (simplificada)

com.funeraria.app/
├── data/
│   ├── local/          → TokenDataStore (DataStore Preferences)
│   ├── remote/
│   │   ├── api/        → Interfaces Retrofit (AuthApi, 8 EntityApis)
│   │   ├── dto/        → Data Transfer Objects
│   │   └── interceptors/ → AuthInterceptor (Bearer token)
│   └── repository/     → Implementaciones de repositorios
├── domain/
│   ├── model/          → Modelos de dominio
│   └── repository/     → Interfaces de repositorio
├── di/                 → Hilt Modules (Network + Repository)
├── navigation/         → Screen sealed class (27 rutas)
├── ui/
│   ├── auth/           → LoginScreen
│   ├── home/           → Dashboard
│   ├── entity/         → 24 pantallas CRUD (8×3)
│   ├── profile/        → ProfileScreen
│   ├── navigation/     → NavGraph + BottomNavBar
│   ├── components/     → Componentes reutilizables
│   ├── theme/          → Color, Type, Shape, Theme
│   └── viewmodel/      → AuthViewModel + 8 EntityViewModels
└── util/               → JwtDecoder
```

---

## ecnologías utilizadas

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Kotlin | 2.0.21 | Lenguaje principal |
| Jetpack Compose | BOM 2024.10.01 | UI declarativa |
| Hilt | 2.52 | Inyección de dependencias |
| Retrofit | 2.11.0 | Cliente HTTP |
| OkHttp | 4.12.0 | Interceptores y logging |
| DataStore | 1.1.1 | Almacenamiento seguro del token |
| Navigation Compose | 2.8.3 | Navegación entre pantallas |
| Coroutines | 1.8.1 | Programación asíncrona |
| Material3 | - | Diseño de la UI |

---
