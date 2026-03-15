# 🏠 Home Inventory System - Domain Documentation

Este documento detalla el descubrimiento de dominio, eventos, entidades y contextos delimitados para el sistema de gestión de inventario doméstico.

## 🎯 1. Objetivo del Sistema
Un sistema para gestionar el inventario del hogar de forma colaborativa (familiar), permitiendo:
- Controlar stock de comida, útiles, higiene y ropa.
- Seguimiento de **fechas de caducidad** y alertas.
- Histórico de **precios de compra** y estimación de costes futuros.
- Gestión de **ubicaciones** (nevera, despensa, etc.) sin jerarquías complejas.
- Generación automática de **listas de la compra** basada en umbrales (*thresholds*).

---

## ⚡ 2. Eventos de Dominio (Domain Events)

### Compras
- `PurchaseRecorded`: Se ha registrado un ticket/compra completa.
- `PurchaseItemDetectedFromOCR`: Un ítem ha sido detectado vía escaneo.
- `PurchaseItemAdded`: Un ítem específico se ha añadido al registro de compra.
- `PurchasePriceRecorded`: Se ha guardado el precio de un producto en una fecha dada.

### Inventario
- `StockAdded`: Nuevas unidades han entrado al sistema.
- `StockConsumed`: Se ha registrado el consumo de un producto.
- `StockAdjustedAfterReview`: Corrección manual de stock tras revisión física.
- `StockMovedToLocation`: Un ítem ha cambiado de ubicación (ej: de despensa a nevera).

### Caducidad y Alertas
- `StockItemExpirationRecorded`: Se ha registrado la fecha de caducidad de una unidad.
- `StockItemAboutToExpire`: Una unidad está próxima a caducar.
- `StockItemExpired`: Una unidad ha caducado.
- `ProductBelowThreshold`: El stock de un producto es inferior al mínimo definido.

### Ropa / Lavandería
- `ClothesAddedToLaundry`: Ropa añadida al cubo de lavar.
- `LaundryCompleted`: Proceso de lavado finalizado.
- `ClothesReturnedToCloset`: Ropa limpia guardada.

---

## 🔄 3. Flujos Reales (Domain Flows)

### Registro de Compra
1. `PurchaseRecorded`
2. → `PurchaseItemAdded`
3. → `StockAdded`
4. → `StockItemExpirationRecorded`
5. → `PurchasePriceRecorded`

### Consumo y Reposición
1. `StockConsumed` (Reducción automática por FIFO)
2. → `ProductBelowThreshold` (Si el stock cae por debajo del umbral)
3. → `ProductAddedToShoppingList`

---

## 🏗️ 4. Modelo de Dominio (Táctico)

### Entidades Principales

#### **Product** (Definición genérica)
- `productId`: UUID
- `name`: string
- `brand`: string
- `variant`: string (ej: entera / semi)
- `category`: string
- `threshold`: int (umbral mínimo para compra)

#### **StockItem** (Unidad física individual)
- `stockItemId`: UUID
- `productId`: ID del producto
- `purchaseId`: ID de la compra de origen
- `expirationDate`: Date
- `location`: string (ej: "Nevera")
- `status`: [Disponible, Consumido, Caducado]

#### **Purchase / PurchaseItem**
- `purchaseId`: UUID
- `date`: Date
- `totalPrice`: Money
- `items`: List<PurchaseItem> (productId, quantity, unitPrice)

### Agregados (Aggregates)
1.  **Product Aggregate**: Gestiona metadatos y umbrales de stock.
2.  **Inventory Aggregate**: Gestiona la colección de `StockItems`, consumos, movimientos y caducidades.
3.  **Purchase Aggregate**: Registro de tickets, histórico de precios e integración OCR.
4.  **ShoppingList Aggregate**: Mantiene la lista de productos faltantes y sugerencias.

---

## 🗺️ 5. Diseño Estratégico

### Subdominios
- **Core Domain**: `Inventory Management` (El corazón del sistema).
- **Supporting Domains**: `Purchase Tracking`, `Price History`, `Expiration Tracking`, `Shopping List`.
- **Generic Domains**: `Users`, `Notifications`.

### Bounded Contexts (Contextos Delimitados)
1.  **Inventory Context**: Gestión de stock físico, ubicaciones y movimientos.
2.  **Product Catalog Context**: Definiciones de productos, marcas y categorías.
3.  **Purchase Context**: Gestión de compras, tickets y precios.
4.  **Shopping Context**: Lógica de generación de lista de la compra y costes estimados.

---

## 📋 6. Reglas de Negocio Críticas
- **Consumo FIFO (First In, First Out)**: El sistema siempre descuenta primero la unidad con la fecha de caducidad más cercana para evitar desperdicio.
- **Inconsistencia de Stock**: Se asume que el stock digital puede diferir del real. El evento `StockAdjustedAfterReview` es la "fuente de verdad" final.
- **Multi-usuario**: Las acciones de registro o consumo pueden ser realizadas por cualquier miembro de la familia sobre el mismo inventario.

---

## 📈 7. Proyecciones (Read Models)
- **ExpiringSoonProjection**: Lista de ítems que caducarán en los próximos X días.
- **LocationStockProjection**: Vista rápida de qué hay en cada sitio (ej: Ketchup: 4 en Despensa, 1 en Nevera).
- **CostEstimationProjection**: Estimación del coste de la próxima compra basada en el histórico de precios y productos bajo el umbral.