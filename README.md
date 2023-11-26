# CHALLENGE DESCRIPTION

En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja
el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas
determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

## TABELA PRICES

* NOTE: I decided to rename the column "price" to "value" because "price" is model's name it would
  be confusing.
* NOTE: I didn't understand the column "PRICE_LIST", seems to be the PK of the table, but it's not
  described as unique due to that I decided to add an ID column and to define all the identifiers as
  unique.
* NOTE: H2 uses YYYY-MM-DD HH:MM:SS as timestamp format, so I changed the format of the dates in
  the table.

| ID | BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | ITEM_PRICE | CURR |
|----|----------|---------------------|---------------------|------------|------------|----------|------------|------|
| 1  | 1        | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 1          | 35455      | 0        | 35.50      | EUR  | 
| 2  | 1        | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 | 2          | 35455      | 1        | 25.45      | EUR  | 
| 3  | 1        | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 | 3          | 35455      | 1        | 30.50      | EUR  | 
| 4  | 1        | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 | 4          | 35455      | 1        | 38.95      | EUR  | 

### Campos:

| Campo      | Descripción                                                                                                                                   |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| ID         | Identificador único del registro de precios.                                                                                                  |
| BRAND_ID   | foreign key de la cadena del grupo (1 = ZARA).                                                                                                |
| START_DATE | rango de fechas en el que aplica el precio tarifa indicado.                                                                                   |
| END_DATE   | rango de fechas en el que aplica el precio tarifa indicado.                                                                                   |
| PRICE_LIST | Identificador de la tarifa de precios aplicable.                                                                                              |
| PRODUCT_ID | Identificador código de producto.                                                                                                             |
| PRIORITY   | Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico). |
| PRICE      | precio final de venta.                                                                                                                        |                                                                                                                       |
| CURR       | iso de la moneda.                                                                                                                             |                                                                                                                           |                                                                                                                             |

## TABELA BRANDS:

* NOTE: I decided to add a table to store the groups of brands.

| ID | NAME |
|----|------|
| 1  | ZARA |

### Campos:

| Campo | Descripción                                  |
|-------|----------------------------------------------|
| ID    | Identificador único de la cadena de tiendas. |
| NAME  | Nombre de la cadena de tiendas.              |

## Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta tal que:

1. Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de
   cadena.
2. Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar,
   fechas de aplicación y precio final a aplicar.

3. Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se
   pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato
   que se considere adecuado para los mismos).

4. Desarrollar unos test al endpoint rest que validen las siguientes peticiones al servicio con los
   datos del ejemplo:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

Se valorará:

Diseño y construcción del servicio.
Calidad de Código.
Resultados correctos en los test.
