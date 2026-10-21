Pensamiento Computacional Programacion 2

Andres David Santafe Lopez

C.C 1023378835

Juan Diego Quitian Rengifo

C.C 1090275278

01D

Abstracción

¿Que se solicita finalmente?

LA empresa RutaVerde cuenta con un sistema poco eficiente para el registro de clientes y administración del parque, por lo que solicitan un sistema que les permita gestionar la información de sus precios, descuentos y paquetes de actividades en el parque.

¿Qué información es relevante?

Cliente: documento, nombre, telefono, correo, pais de origen, idioma preferido.

Guia: identificación, nombre, idioma habla, telefono, tarifa dia.

Actividad: código, nombre, municipio, duración en horas, precio por persona, cupo maximo dia, dificultad, estado,

Paquete: codigo, nombre, porcentaje descuento

Reserva: cliente, producto, fecha, numero de viajeros, guian, opcionales (transporte al hotel y observaciones).

PagoAndinoAPI:consecutivo.

Voucher: encabezado con nombre de la agencia, RTN detalles de reserva politica de cancelación.

Agencia: nombre comercial, NIT, número de Registro Nacional de Turismo (RNT), dirección, teléfono, correo electrónico y página web.

## ¿Como se agrupa la información relevante?

| Grupo | Clase | Atributos |
| --- | --- | --- |
| Registro | Cliente. Guia | documento, nombre, telefono, correo, pais de origen, idioma preferido. |
| Producto | Actividad, Paquete | código, nombre, municipio, duración en horas, precio por persona, cupo maximo por dia, |


|   |   | dificultad, estado, porcentaje descuento. |
| --- | --- | --- |
| Reservas | Reserva | cliente, producto, fecha, numero de viajeros, guia, opcionales (transporte al hotel y observaciones), |
| Pasarela de pago | PagoAndinoAPI | consecutivo |
| Voucher | Voucher(sus formas de emision) | Formato |
| Regla de negocio | Agencia | nombre comercial, NIT, número de Registro Nacional de Turismo (RNT), dirección, teléfono, correo electrónico y página web. |

¿Qué funcionalidades solicita finalmente?

Registro

Registrar Clientes

RF-01: registrar clientes con su documento de identidad o pasaporte, nombre completo, teléfono, correo electrónico, país de origen e idioma preferido.

RF-02: registrar guías con identificación, nombre, idiomas que habla, teléfono y tarifa por día.

RF-03: registrar actividades con código, nombre, municipio, duración en horas, precio por persona, cupo máximo por día, nivel de dificultad y estado.

- RF-04: registrar y agrupar paquetes en otros paquetes y actividades con código, nombre y un porcentaje de descuento propio que va del 0% al 20%.

- RF-05: Consultar de el precio, la duración y la lista de actividades de cualquier producto.

- RF-06: Registrar reservas asociando con sus atributos obligatorios y opcionales.

- RF-07: Calcular el valor de la reserva multiplicando el precio por persona del producto por el número de viajeros, más los servicios opcionales incluidos.

- RF-08: Verificar que el número de tarjeta sea válido utilizando el algoritmo de Luhn.

- RF-09: Procesar los cobros a través de la pasarela externa PagoAndinoAPI.

- RF-10: Confirmar una reserva siguiendo un orden exacto preestablecido.

- RF-11: Emitir el voucher según el medio donde va.

- RF-12: Consultar los ingresos generados por reservas confirmadas cuyo viaje se realiza durante un periodo determinado.


*RF-13: Consultar cuál es el producto que más viajeros ha movido en el sistema recorriendo y acumulando los datos de las reservas confirmadas.*

| Frase | No regla | Regla de negocio |
| --- | --- | --- |
| Un paquete no puede contenerse a sí mismo, ni directa ni indirectamente |   | RN-001 Un paquete de viaje no puede repetirse a si mismo |
| un mismo plan no puede incluir dos veces la misma actividad |   | RN-002 No se puede repetir la misma actividad en el mismo producto |
| No puede existir una reserva sin cliente ni sin producto |   | RN-003 La reserva no se pude dar sin un cliente ni producto |
| la fecha del viaje no puede estar en el pasado |   | RN-004 La fecha del viaje no se pude desactualizar |
| el grupo debe tener entre 1 y 15 viajeros |   | RN-005 El rango del grupo debe estar entre 1 y 15 |
| el guía asignado debe hablar el idioma preferido del cliente |   | RN-006 El guía debe ser asignado según el idioma preferido del cliente registrado |
| no se puede reservar un producto que incluya alguna actividad suspendida |   | RN-007 No pueden haber actividades suspendidas en un plan |
| La agencia no puede modificar el código de la pasarela |   | RN-008 No se puede modificar la pasarela de pago utilizada |
| - |   | RN-009 El número de tarjeta El número de tarjeta debe tener entre 13 y 19 dígitos y verificarse por el logaritmo de luhn. |
| - |   | RN-010 el cliente recibe un correo con el motivo y no se descuenta ningún cupo si los pasos para realizar una reserva fallan |


| Módulo | Clases | RF | RN |
| --- | --- | --- | --- |
| Registro | Cliente, Guia | RF-01, RF-02 | RN-006 |
| Productos | Actividad, Paquete RF-03, RF-04, RF- | 05 | RN-001, RN-002, RN-007 |
| Reserva | Reserva, ReservaBuilder | RF-06, RF-07 | RN-003, RN-004, RN-005, RN-006, RN-007 |
| Pasarela | PagoAndinoAPI, PasarelaPago | RF-08, RF-09 | RN-008, RN-009 |
| Voucher | ReservaFacade, Voucher (y variantes por canal) | RF-10, RF-11 | RN-010 |
| Consulta | Agencia | RF-12, RF-13 | Ninguna |

¿Qué debo hacer para probar las funcionalidades?

| Módulo | Regla | Caso | Resultado |
| --- | --- | --- | --- |
| Productos | RN-001 | Paquete de viaje que se contiene a sí mismo (directa o indirectamente) | Error / Excepción de estructura circular |
| Productos | RN-002 | Incluir dos veces la misma actividad en el mismo plan | No se puede realizar la misma actividad en el mismo plan |
| Reserva | RN-003 | Reserva con Cliente == null || producto == null | Error / Excepción por datos obligatorios faltantes |
| Reserva | RN-004 | Fecha de viaje establecida en el pasado | Fecha no disponible / Error |
| Reserva | RN-005 | Grupo de viajeros con 16 personas (Grupo == 16) | No es posible / Error de rango de viajeros (1 a 15) |
| Registro / Reserva | RN-006 | Cliente con idioma preferido Español y Guía asignado que habla Inglés | Se debe asignar otro guía al grupo |


| Productos / Reserva | RN-007 | Intentar reservar un producto que incluye una actividad con estado Suspendido | Error / No se puede reservar producto con actividades suspendidas |
| --- | --- | --- | --- |
| Pasarela | RN-008 | Intentar modificar o utilizar una pasarela distinta a la permitida | Error de integración |
| Pasarela | RN-009 | Número de tarjeta con formato inválido o que no cumple con el algoritmo de Luhn | Error de validación de tarjeta |

¿Qué puedo reutilizar para la solución?

| Enunciado | Problema | Patrón | Actores | Clases que nacen |
| --- | --- | --- | --- | --- |
| Cada reserva se registra con el cliente, el producto reservado (una actividad o un paquete), la fecha del viaje y el número de viajeros, que son obligatorios. Y opcionales | La clase contiene muchos atributos obligatorios y opcionales que deben ser validados | Builder | Builder: ReservaBuilder Product: Reserva | ReservaBuilder |
| Lo que cambia es el detalle según el canal desde donde se confirma (asesor, kiosco, | El voucher tiene diferentes modos de ser emitidos ademas de que | Abstract Factory | AbstractFactory: GeneradorVoucherFactory ConcreteFactory: GeneradorPantalla, GeneradorKiosco, GeneradorMovil | GeneradorPantalla, GeneradorKiosco, GeneradorMovil |


| aplicación móvil). | contienen el mismo formato |   |   |   |
| --- | --- | --- | --- | --- |
| La agencia no puede modificar el código de la pasarela: el proveedor entrega la clase PagoAndinoAPI. | La agencia no pude cambiar la api suministrado, necesita poder traducirla | Adapter | Target: PasarelaPago Adaptee: PagoAndinoAPI Adapter: PagoAndinoAdapter | PagoAndinoAdapter, PasarelaPago |
| Confirmar una reserva implica, en este orden exacto. Si cualquiera de los pasos falla, el cliente recibe un correo con el motivo y no se descuenta ningún cupo. | Para efectuar una reserva requiere de validar diferentes procesos distintos, se necesita controlar el orden | Facade | Facade: ReservaFacade Subsystem: Reserva, ValidadorLuhn, PasarelaPago, GeneradorVoucher, Agencia | ReservaFacade |
| La agencia agrupa sus actividades en paquetes. | Hay paquetes dentro de otros paquetes y la agencia debe interpretar por igual Cualquier tipo de producto (Actividad o paquete) |   | Component: Producto Composite Composite: Paquete Leaf: Actividad | Producto, Paquete, Actividad |

# Diagrama de clases UML

![](/docs/UML.jpg)

# Video Youtube

### https://youtu.be/NkDhGdIr5T8