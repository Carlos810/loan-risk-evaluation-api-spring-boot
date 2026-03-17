# Ordenaris Risk Engine
Motor de evaluación de riesgo crediticio empresarial desarrollado en Spring Boot.
## Introducción
Este proyecto implementa un motor de reglas que permite evaluar el riesgo de otorgar crédito a una empresa a partir de información financiera, historial de pagos y situación legal. El sistema recibe una solicitud de evaluación y aplica un conjunto de reglas de negocio para determinar un nivel de riesgo.
### Reglas evaluadas
1. Deuda Activa: Si la empresa posee una deuda vencida mayor a 90 días → RECHAZADO
2. Alta Solicitud vs Ventas: Si el monto solicitado es mayor a 8 veces las ventas promedio → ALTO
3. Empresa Nueva: Si la empresa tiene menos de 18 meses de operación → mínimo MEDIO
4. Demanda Legal Abierta: Si la empresa tiene un proceso legal activo → ALTO
5. Historial Excelente: Si la empresa tiene historial de pagos puntual → reduce el riesgo
6. Producto Estricto: Si el producto es ARRENDAMIENTO_FINANCIERO → incrementa el riesgo
## Casos límite considerados
- Si existe deuda vencida mayor a 90 días, la evaluación se detiene y el resultado es RECHAZADO
- Si múltiples reglas aplican, se toma el nivel de riesgo más alto
- Si ninguna regla impacta, el riesgo permanece en BAJO
- Validación de entrada para evitar valores inválidos como producto financiero incorrecto
- El sistema es extensible sin afectar el comportamiento actual
## Estructura del proyecto
controller → exposición del API REST | engine → motor de evaluación de riesgo | rules → reglas de negocio independientes | providers → fuentes de datos desacopladas | model → objetos de dominio y DTOs | context → contenedor de datos para evaluación
## Diseño y arquitectura
El sistema está construido bajo Programación Orientada a Objetos (POO), donde cada componente tiene una responsabilidad clara y separada. Las reglas se modelan como clases independientes, permitiendo encapsular la lógica de negocio y facilitar su mantenimiento.
### Principios SOLID
SRP: cada clase tiene una única responsabilidad (reglas, providers, engine) | OCP: se pueden agregar nuevas reglas sin modificar el motor principal | LSP: todas las reglas implementan la misma interfaz y son intercambiables | ISP: interfaces específicas para cada provider | DIP: el motor depende de abstracciones y no de implementaciones concretas
### Patrones de diseño aplicados
Strategy Pattern: cada regla implementa la interfaz IRiskRule permitiendo comportamientos intercambiables | Chain of Responsibility: el motor evalúa una lista de reglas en secuencia | Dependency Injection: Spring inyecta automáticamente reglas y providers
## Cómo ejecutar el proyecto
Requisitos: Java 21 y Maven
Ejecutar con: mvn spring-boot:run
O ejecutar la clase principal MvpApplication desde el IDE
## Swagger
Acceder a la documentación interactiva en: http://localhost:8086/swagger-ui.html
## Endpoint principal
POST /risk/evaluate
### Request
{ "empresaId": "EMP-001", "montoSolicitado": 90000, "productoFinanciero": "ARRENDAMIENTO_FINANCIERO", "fechaSolicitud": "2026-03-16" }
### Response
{ "nivelRiesgo": "ALTO", "reglasEvaluadas": [ { "aplicado": true, "riskLevel": "ALTO", "mensaje": "Producto de alto riesgo" } ], "motivoFinal": "Producto de alto riesgo" }
## Extensibilidad
Para agregar una nueva regla solo se requiere crear una clase que implemente IRiskRule y anotarla con @Component. Spring la incluirá automáticamente en el flujo de evaluación sin necesidad de modificar el core del sistema.

## Manual de pruebas (mock vs request)
Para facilitar pruebas sin base de datos, los providers están implementados como mocks. A continuación se describe cómo modificar los mocks y qué request enviar para validar cada regla.
### 1. Deuda Activa → RECHAZADO
MockHistorialPagosProvider:
return new HistorialPagos(true, false);
Request:
{ "empresaId": "EMP-001", "montoSolicitado": 50000, "productoFinanciero": "LINEA_OPERATIVA", "fechaSolicitud": "2026-03-16" }
Resultado esperado: RECHAZADO
### 2. Alta Solicitud vs Ventas → ALTO
MockDatosContablesProvider:
return new DatosContables(new BigDecimal("10000"), new BigDecimal("50000"), new BigDecimal("20000"));
Request:
{ "empresaId": "EMP-002", "montoSolicitado": 90000, "productoFinanciero": "LINEA_OPERATIVA", "fechaSolicitud": "2026-03-16" }
Resultado esperado: ALTO
### 3. Empresa Nueva → MEDIO
MockVerificacionLegalProvider:
return new EstadoLegal(false, LocalDate.now().minusMonths(6));
Request:
{ "empresaId": "EMP-003", "montoSolicitado": 20000, "productoFinanciero": "LINEA_OPERATIVA", "fechaSolicitud": "2026-03-16" }
Resultado esperado: MEDIO
### 4. Demanda Legal → ALTO
MockVerificacionLegalProvider:
return new EstadoLegal(true, LocalDate.of(2020,1,1));
Request:
{ "empresaId": "EMP-004", "montoSolicitado": 20000, "productoFinanciero": "LINEA_OPERATIVA", "fechaSolicitud": "2026-03-16" }
Resultado esperado: ALTO
### 5. Historial Excelente → BAJO
MockHistorialPagosProvider:
return new HistorialPagos(false, true);
Request:
{ "empresaId": "EMP-005", "montoSolicitado": 20000, "productoFinanciero": "LINEA_OPERATIVA", "fechaSolicitud": "2026-03-16" }
Resultado esperado: BAJO
### 6. Producto Estricto → ALTO
Request:
{ "empresaId": "EMP-006", "montoSolicitado": 20000, "productoFinanciero": "ARRENDAMIENTO_FINANCIERO", "fechaSolicitud": "2026-03-16" }
Resultado esperado: ALTO


## Conclusión
La solución propone un diseño simple, desacoplado y extensible que permite evolucionar fácilmente las reglas de negocio sin afectar el motor principal, manteniendo claridad, mantenibilidad y buenas prácticas de desarrollo.

