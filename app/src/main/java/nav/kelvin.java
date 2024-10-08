package nav;

public class kelvin extends grado
{
    public kelvin(Double valor)
    {
        this.setUnidad("K");
        this.setValor(valor);
    }

    public kelvin parse(centigrado centigrado)
    {
        return new kelvin(centigrado.getValor()+273.15);
    }

    public kelvin parse(farenheit farenheit)
    {
        return new kelvin((farenheit.getValor()-32)/1.8+273.15);
    }
}
