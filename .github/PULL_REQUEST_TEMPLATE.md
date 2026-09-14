<!-- template-id: github-pull-request -->
<!-- template-version: 1.0.0 -->
<!-- template-origin: pelu-catalog -->

## Resumen

<!-- Explica qué cambia y por qué. -->

## Tipo de cambio

- [ ] `feat`: nueva funcionalidad
- [ ] `fix`: corrección
- [ ] `docs`: documentación o contratos
- [ ] `test`: pruebas
- [ ] `refactor`: reestructuración
- [ ] `perf`: mejora de rendimiento
- [ ] `build`: build o dependencias
- [ ] `ci`: automatización
- [ ] `chore`: mantenimiento
- [ ] `BREAKING CHANGE`: cambio incompatible

## Tarea relacionada

<!-- Usa "Closes #123", enlaza la tarea o indica "No aplica". -->

Closes #

## Alcance

- Componente o sistema modificado: <!-- Nombre del componente o sistema. -->
- Ámbitos afectados: <!-- Módulos, paquetes o capacidades. -->
- Contraparte afectada: <!-- SuperMax o "Ninguna". -->

## Impacto distribuido

- Transporte o mecanismo afectado: <!-- TCP, UDP o "Ninguno". -->
- Mensajes afectados: <!-- Nombres concretos o "Ninguno". -->
- Campos o identificadores modificados: <!-- Campos concretos o "Ninguno". -->
- Compatibilidad con SuperMax: <!-- Compatible, requiere cambio coordinado o no aplica. -->
- Contrato actualizado: <!-- docs/EntregaYa_v3.md o "No aplica". -->

## Validación

### Comandos ejecutados

```text
mvn clean package
```

### Resultado

<!-- Describe el resultado real, las pruebas manuales y cualquier limitación. -->

## Riesgos y fallos considerados

- [ ] Timeout o desconexión de operaciones TCP.
- [ ] Reintentos, duplicados e idempotencia mediante `requestId`.
- [ ] Transición inválida de pedido, pago o asignación de repartidor.
- [ ] Actualizaciones UDP de ubicación perdidas, duplicadas o desordenadas.
- [ ] Compatibilidad de contrato con SuperMax.
- [ ] No aplica; la explicación está incluida en la PR.

## Checklist

- [ ] El título sigue la convención definida por el repositorio.
- [ ] La PR tiene un único propósito.
- [ ] No contiene credenciales, secretos ni datos sensibles.
- [ ] La compilación o validación aplicable finaliza correctamente.
- [ ] Las pruebas fueron añadidas o actualizadas cuando corresponde.
- [ ] La documentación refleja el comportamiento implementado.
- [ ] Los contratos, esquemas o interfaces continúan siendo consistentes.
- [ ] Los cambios incompatibles están identificados explícitamente.
