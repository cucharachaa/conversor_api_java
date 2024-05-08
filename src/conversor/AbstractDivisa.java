package conversor;

public abstract class AbstractDivisa{
    //los campos de clase son diferentes para cada instancia que herede de esta clase
    protected String nombreDivisa;
    protected String pais;
    protected String siglas;

    protected abstract String getNombreDivisa();
    protected abstract String getPais();
    protected abstract String getSiglas();
}
