package nav;

public class centigrado extends grado
{
    public centigrado(Double valor)
    {
        this.setUnidad("C");
        this.setValor(valor);
    }

    public centigrado parse(kelvin kelvin)
    {
        return new centigrado(kelvin.getValor() - 273.15);
    }

    public centigrado parse(farenheit farenheit)
    {
        return new centigrado((farenheit.getValor() - 32) / 1.8);
    }
}
