# companies-crud-spring-ecosystem

Solucion para un CRUD de companias que permite crear, eliminar, consultar y editar. 

Este proyecto incluye los siguientes funcionalidades
  * Companies: funcionalidad principal (companies-crud)
  * Circuit breaker: en caso de que el programa principal se encuentre caido, las llamadas se redirigen al companies-crud-fallback
  * Eureka: servidos de eureka donde se registran todos los programas (registry-server)
  * Gateway: Todas las llamadas entran por este sistema
  * Auth server: servidos que se encarga de la autenticacion
  * Report server: un servidor que se encarga de realizar reportes teniendo en cuenta la informacion del companies-crud
  * Report listener: servidor que toma lo mensajes de kafka dejados por el report server para guardarlos en una mongo db con el formato del reporte (report-listener)
  * COnfig server: servidor que lee las configuraciones de un reporte de github para que el resto pueda utilizarlas
    
