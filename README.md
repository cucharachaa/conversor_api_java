# Conversor de monedas muy sencillo utilizando la API pública de exchange rate

Caso de uso
```java
public class Main {
    public static void main(String[] args) throws IOException {
        Moneda moneda = new Moneda("Chile", 5000);
        System.out.println(moneda.convertirDivisa(new Moneda("Japan", 20)));
    }

}
```
Al crear una moneda va a buscar en data/divisasSegunPais.json (archivo que está en ingles) para obtener las siglas y el nombre de la divisa completo.
Posteriormente al completar la información realiza una solicitud a la API la cual entrega el ratio de conversion de la moneda principal a la solicitada y retorna el total.


