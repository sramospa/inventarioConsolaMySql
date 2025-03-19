# Inventario en Consola con MySQL

## 📌 Descripción
Este es un proyecto de práctica en **Java** que implementa un sistema de gestión de inventarios utilizando **MySQL** como base de datos. La aplicación permite gestionar 
productos a través de una interfaz en consola, aplicando conceptos de **POO, JDBC, colecciones e interfaces**.

## 🛠️ Tecnologías utilizadas
- **Java** (JDK 8 o superior)
- **MySQL** (Base de datos relacional)
- **JDBC** (Conexión con MySQL)
- **Maven** (Gestión de dependencias, si aplica)

## ⚙️ Instalación y configuración
### 1️⃣ Clonar el repositorio
```sh
git clone https://github.com/sramospa/inventarioConsolaMySql.git
cd inventarioConsolaMySql
```

### 2️⃣ Configurar la base de datos
1. Crear una base de datos en MySQL:
```sql
CREATE DATABASE inventario;
```
2. Importar el script de la base de datos ubicado en `database`:
```sh
mysql -u tu_usuario -p inventario < database/inventario_categorias.sql
mysql -u tu_usuario -p inventario < database/inventario_productos.sql
mysql -u tu_usuario -p inventario < database/inventario_usuarios.sql

```
3. Configurar los datos de conexión en el archivo **config.properties** (o dentro del código Java):
```properties
DB_URL=jdbc:mysql://localhost:3306/inventario_db
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
```

### 3️⃣ Ejecutar el proyecto
Compilar y ejecutar desde la terminal:
```sh
javac -cp .:mysql-connector-java.jar src/Main.java
java -cp .:mysql-connector-java.jar src.Main
```
(Si usas Windows, reemplaza `:` por `;` en el classpath.)

## 🚀 Funcionalidades
✅ Agregar productos al inventario  
✅ Listar productos registrados  
✅ Actualizar información de productos  
✅ Eliminar productos  
✅ Consultar productos por nombre o categoría  

## 📝 Estructura del proyecto
```
📂 inventarioConsolaMySql
 ├── 📂 src
 │   ├── Main.java
 │   ├── 📂 models
 │   │   ├── Producto.java
 │   ├── 📂 database
 │   │   ├── ConexionDB.java
 │   ├── 📂 services
 │       ├── InventarioService.java
 ├── 📂 database
 │   ├── inventario.sql  # Script con estructura y datos de prueba
 ├── README.md
 ├── .gitignore
 ├── pom.xml (si usa Maven)
```

## 📌 Contribución
¡Las contribuciones son bienvenidas! Si deseas mejorar este proyecto:
1. **Haz un fork** del repositorio
2. **Crea una rama** con tu mejora (`git checkout -b feature/nueva-funcionalidad`)
3. **Sube tus cambios** (`git commit -m 'Agregada nueva funcionalidad'` y `git push origin feature/nueva-funcionalidad`)
4. **Abre un Pull Request**

## 📜 Licencia
Este proyecto está bajo la licencia **MIT**. Puedes ver más detalles en el archivo `LICENSE`.

## ✉️ Contacto
Creado por **[@sramospa](https://github.com/sramospa/)** – ¡Si tienes dudas o sugerencias, contáctame! 😊
