package nav;

public class farenheit extends grado
{
    public farenheit(Double valor)
    {
        this.setUnidad("F");
        this.setValor(valor);
    }

    public farenheit parse(kelvin kelvin)
    {
        return new farenheit((kelvin.getValor()-273.15)*1.8+32);
    }

    public farenheit parse(centigrado centigrado)
    {
        return new farenheit(centigrado.getValor()*1.8+32);
    }
}
