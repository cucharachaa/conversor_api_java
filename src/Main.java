import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import conversor.Moneda;


import java.io.Reader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

/*
    Hecho por cucharacha 08-05-2024 :D
*/

public class Main {
    public static void main(String[] args) throws IOException {
        Moneda moneda = new Moneda("Chile", 5000);
        System.out.println(moneda.convertirDivisa(new Moneda("Japan", 20)));
    }

}
