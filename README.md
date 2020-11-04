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
Aplicación en la que podemos consultar una lista de personajes de Marvel

- Lista de personajes: Muestra un listado de los personajes, se van cargando de 20 en 20.
  Esta pantalla solo se actualiza con nueva información del servidor cuando entras por primera vez o cuando ejecutas la opción the pull to refresh.

- Detalle de personaje: Muestra un detalle más específico de cada personaje


<a name="requirements"></a>
## Requerimientos
- Java 8

- MinSdk 21+ (Android 5.0 or higher)

- Clave privada y pública
  Para obtener acceso a los datos necesitamos crear los api key (https://developer.marvel.com/).
  Una vez que las tengamos generadas debemos agregarlas al fichero build.gradle
      buildConfigField ("String", "MARVEL_PUBLIC_KEY", '"ADD KEY"')
      buildConfigField ("String", "MARVEL_PRIVATE_KEY", '"ADD KEY"')

  ** En caso de no tener las claves correctamente configuradas solamente podrá acceder a la aplicación en modo MOCK


<a name="dependencies"></a>
## Versiones
Para actualizar o comprobar alguna versión de las dependencias agregadas puede hacerlo consultando el fichero versions.gradle


<a name="modes"></a>
## Modos
Tenemos dos tipos de productos disponibles, el cual puede cambiar en la opción de "Build Variant" dentro de Android Studio

- Mock: Carga los datos de un fichero Json local y no comprueba su conexión a internet (Para pruebas locales)

- Dev: Carga los datos del backend configurado en el fichero build.gradle (necesario api key)


<a name="architecture"></a>
## Arquitectura
La aplicación está dividida en 4 paquetes
1. core -- Lógica y recursos compartidos
2. di -- Dependencias de aplicación
3. data -- Acceso a datos (Data - Domain Layer)
4. features -- UI organizado por modelos de negocios y casos de uso (UI Layer)

UI Layer:
Se utiliza MVVM (view(fragments) -> viewmodel -> use case)  
Las vistas solicitan datos al viewmodel quien a su vez pide los datos al caso de uso correspondiente y los publica utilizando LiveData.
Para el acceso desde fragments a xml se utiliza viewBinding

Data - Domain Layer  
Se utiliza patrón Repository implementando solamente la fuente remota, se utilizan caso de uso funcional con el objeto Either donde siempre se devuelve un resultado (OK, Failure)

Notes:
- Dagger2: Gestión de dependencias
- Retrofit2 & moshi: Acceso y mapeo de datos
- Jetpack components(viewmodel, livedata, lifecycle, navigation component)
- Glide: Carga de imágenes


<a name="todo"></a>
## TODO lista
- Filtros

- Mejora de interface

- Tratamiento de errores con más detalles

- UI testing

- Offline mode

- API key Manager



