package conversor;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

//clase encargada de obtener datos de la API y archivos
public class DivisaScrapper{
    JsonObject paisesData;

    public DivisaScrapper(String pais){
            // Lee el archivo JSON como un objeto Java
            try {
                Reader reader = Files.newBufferedReader(Paths.get("data/divisasSegunPais.json"));
                // Carga los datos de los paises a un objeto de java con formato del Json leido
                this.paisesData = new Gson().fromJson(reader, JsonObject.class);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    //metodo que obtiene nombre divisa del Json
    public String obtenerNombreDivisaSegunPais(String pais){
        JsonObject paisSeleccionado = this.paisesData.get(pais).getAsJsonObject();
        return paisSeleccionado.get("currencyName").getAsString();
    }

    //metodo que obtiene siglas de la divisa segun pais del Json
    public String obtenerSiglasDivisaSegunPais(String pais){
        JsonObject paisSeleccionado = this.paisesData.get(pais).getAsJsonObject();
        return paisSeleccionado.get("currencyCode").getAsString();
    }


    public double obtenerFactorDeConversion(String siglas1, String siglas2) throws IOException {
        //convertir https://v6.exchangerate-api.com/v6/ffbe60db397a4afc1dd86fcf/pair/CLP/USD
        // Making Request
        String api_key = "ffbe60db397a4afc1dd86fcf";
        String endpoint = """
                https://v6.exchangerate-api.com/v6/%s/pair/%s/%s
                """.formatted(api_key, siglas1, siglas2);
        URL url = new URL(endpoint);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        // Accessing object
        String req_result = jsonobj.get("conversion_rate").getAsString();
        System.out.println(req_result);
        return Double.parseDouble(req_result);
    }
}
