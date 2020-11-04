# Marvel App

# Tabla de contenido
+ [Resumen](#sumary)
+ [Requerimientos](#requirements)
+ [Versiones](#dependencies)
+ [Modos](#modes)
+ [Arquitectura](#architecture)
+ [TODO lista](#todo)

<a name="sumary"></a>
## Resumen
Aplicación en la que podemos consultar una lista de personajes, cuenta con 2 pantallas (Lista y Detalle de personaje )

- Lista de personajes: Muestra un listado de los personajes, se van cargando de 20 en 20 una vez.
  Esta pantalla solo se actualiza con nueva información del servidor cuando entras por primera vez o cuando ejecutas la opctión the pull to refresh.

- Detalle de personaje: Muestra un detalle más específico de cada personaje:


<a name="requirements"></a>
## Requerimientos
- Java 8

- MinSdk 21+ (Android 5.0 or higher)

- Clave privada y pública


<a name="dependencies"></a>
## Versiones
Para actualizar o comprobar alguna versión de las dependencias agregadas puede hacerlo en el fichero versions.gradle


<a name="modes"></a>
## Modos
Tenemos dos tipos de productos disponibles, el cual puede cambiar en la opción de "Build Variant" dentro de Android Studio

- Mock: Carga los datos de un fichero Json local y no comprueba su conexión a internet (Para pruebas locales)

- Dev: Carga los datos del backend configurado en el fichero build.gradle

<a name="architecture"></a>
## Arquitectura
//TODO

<a name="todo"></a>
## TODO lista
- Filtros

- Mejora de interface

- Tratamiento de errores con más detalles

- UI testing

- Offline mode


