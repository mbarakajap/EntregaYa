# Sistema 1: Entrega Ya

Comparando: Wait review

| Santiago Adrián Pérez Barreto | 5610478 |
| --- | --- |
| Juan José Augusto Riverón Meza | 5677678 |
| Lucas Enmanuel Torres Amarilla | 7014594 |

# Organización

**Empresa dueña:** EntregaYa S.A. Su objetivo es facilitar el descubrimiento, compra y entrega a domicilio de productos de comercios afiliados.

**Sistema:** Plataforma de Pedidos EntregaYa. Su objetivo es gestionar el ciclo de vida de pedidos: creación, pago, despacho, seguimiento y cierre. Es autónomo: EntregaYa administra sus propios datos de usuarios, pedidos, pagos y repartidores. Cada instancia interactúa directamente con el sistema del comercio/supermercado.

# Servicios

## 1. Registrar pedido

El sistema registra un pedido y devuelve una confirmación de creación.

### **Entrada**

`requestId`, `cliente {nombre, direccion}`, `items [{sku, cantidad}]`, `montoProductos`

```json
{
  "tipo": "REGISTRAR_PEDIDO",
  "requestId": "req-a1-01",
  "cliente": {
    "nombre": "Ana Gómez",
    "direccion": "Av. España 456"
  },
  "items": [
    {"sku": "ARROZ-1K", "cantidad": 2}
  ],
  "montoProductos": 17000
}
```

### **Salida**

`ok`, `requestId`, `pedidoId`, `estadoPedido`, `costoEnvio`, `total` , `timestamp` 

```json
{
  "ok": true,
  "requestId": "req-a1-01",
  "pedidoId": "PED-101",
  "estadoPedido": "CREADO",
  "costoEnvio": 8000,
  "total": 25000,
  "timestamp": "2026-08-27T21:50:00-03:00"
}
```

## 2. Confirmar pago

Registra el resultado del cobro del pedido y guarda el pedido como pagado.

### **Entrada**

`requestId`, `pedidoId`, `transaccionId` , `estadoTransaccion`, `monto`, `metodoPago`

```json
{
  "tipo": "CONFIRMAR_PAGO",
  "requestId": "req-a2-01",
  "pedidoId": "PED-101",
  "transaccionId": "TRA-001",
  "estadoTransaccion": "PAGADO"
  "monto": 25000,
  "metodoPago": "TARJETA"
}
```

### **Salida**

`ok`, `requestId`, `pedidoId`, `estadoTransaccion`, `timestamp` 

```json
{
  "ok": true,
  "requestId": "req-a2-01",
  "pedidoId": "PED-101",
  "estadoTransaccion": "PAGADO",
  "timestamp": "2026-08-27T21:50:00-03:00"
}
```

## 3. Consultar estado

Permite conocer en qué etapa se encuentra el pedido y el repartidor asignado.

### **Entrada**

`requestId`, `pedidoId`

```json
{
  "tipo": "GET_ESTADO_PEDIDO",
  "requestId": "req-a3-01",
  "pedidoId": "PED-101"
}
```

### **Salida**

`ok`, `requestId`, `pedidoId`, `estadoPedido`, `repartidor {id, nombre}` , `timestamp` 

```json
{
  "ok": true,
  "requestId": "req-a3-01",
  "pedidoId": "PED-101",
  "estadoPedido": "EN_CAMINO",
  "repartidor": {
	  "id": "REP-001",
	  "nombre": "Carlos Gómez"
	  },
	"timestamp": "2026-08-27T21:50:00-03:00"
}
```

## 4. Solicitar repartidor

El supermercado solicita el retiro cuando el paquete está listo para enviar.

### **Entrada**

`requestId`, `pedidoId`, `direccionRetiro`

```json
{
  "tipo": "SOLICITAR_REPARTIDOR",
  "requestId": "req-a4-01",
  "pedidoId": "PED-101",
  "direccionRetiro": "SuperMax Local Central"
}
```

### **Salida**

`ok`, `requestId`, `pedidoId`, `estadoPedido`, `repartidor`, `etaMinutos`

```json
{
  "ok": true,
  "requestId": "req-a4-01",
  "pedidoId": "PED-101",
  "estadoPedido": "REPARTIDOR_ASIGNADO",
  "repartidor": {
	  "id": "REP-001",
	  "nombre": "Carlos Gómez"
	  },
  "etaMinutos": 10
}
```

## 5. **Actualización de ubicación**

El sistema registra un pedido y devuelve una confirmación de creación.

### **Entrada**

Posición GPS del repartidor

### **Salida**

`tipo`, `pedidoId`, `secuencia`, `latitud`, `longitud`, `timestamp`

```json
{
  "tipo": "ACTUALIZACION_UBICACION",
  "pedidoId": "PED-101",
  "secuencia": 18,
  "latitud": -25.286,
  "longitud": -57.585,
  "estadoPedido": "EN_CAMINO",
  "timestamp": "2026-08-27T21:50:00-03:00"
}
```