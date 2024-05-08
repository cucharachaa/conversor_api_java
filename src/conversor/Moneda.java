package conversor;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Moneda extends AbstractDivisa{
    DivisaScrapper ds;
    double cantidad;
    String paisFormateado;

    public Moneda(String pais, double cantidad){
        this.paisFormateado = this.formateaNombrePais(pais);
        ds = new DivisaScrapper(this.paisFormateado);
        this.cantidad = cantidad;
        this.pais = this.paisFormateado;
        this.nombreDivisa = ds.obtenerNombreDivisaSegunPais(this.paisFormateado);
        this.siglas = ds.obtenerSiglasDivisaSegunPais(this.paisFormateado);
    }


    @Override
    public String getNombreDivisa() {
        return this.nombreDivisa;
    }

    @Override
    public String getPais() {
        return this.pais;
    }

    @Override
    public String getSiglas() {
        return this.siglas;
    }

    @Override
    public String toString(){
        return """
                Soy una moneda de "%s", me llaman "%s" y mis siglas son "%s"
                """.formatted(this.pais, this.nombreDivisa, this.siglas);
    }

    //INTPUT: foo foo bar foo
    //OUTPUT: Foo Foo Bar Foo
    //TODO: terminar la funcion
    private String formateaNombrePais(String pais){
        //metodo 1: regex
        Pattern pattern = Pattern.compile("\\b(\\w)");
        Matcher matcher = pattern.matcher(pais);
        StringBuffer resultado = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(resultado, matcher.group().toUpperCase());
        }
        matcher.appendTail(resultado);
        return resultado.toString();

        //metodo 2: manual
        /*
        String tmpPais = pais;
        //Si no contiene espacios en blancos, capitaliza la primera letra.
        if (!tmpPais.contains(String.valueOf(" "))){
            tmpPais = tmpPais.substring(0,1).toUpperCase() + tmpPais.substring(1).toLowerCase();
            return tmpPais;
        }
        //Caso que no se cumpla EJ: hola miau ola --> Hola Miau Hola
        StringBuilder resultado = new StringBuilder();
        String[] palabras = pais.split(" ");

        for (String palabra : palabras){
            if (!palabra.isEmpty()){
                char primeraLetra = Character.toUpperCase(palabra.charAt(0)); //pone en mayuscula la primera letra
                String restante = palabra.substring(1);
                resultado.append(primeraLetra).append(restante).append(" ");

            }
        }
        System.out.println("resultado: "+resultado.toString().trim());
        */
    }

    public String convertirDivisa(Moneda otraMoneda) throws IOException {
        //convierte de una divisa a otra EJ: USD a CLP segun la cantidad que haya
        double factorDeConversion = ds.obtenerFactorDeConversion(this.siglas, otraMoneda.siglas);
        double formula = this.cantidad * factorDeConversion;
        return """
                Al convertir %.2f %s a %s obtengo %.2f %s
                """.formatted(this.cantidad, this.siglas, otraMoneda.nombreDivisa, formula, otraMoneda.siglas);
    }


}
