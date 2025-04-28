package ejercicio7JUnit;

import java.util.ArrayList;
import java.util.List;

import ejercicio7JUnit.Movimiento.Tipo;

public class CuentaBancaria {
	private double saldo;
	private List<Movimiento> historial; 
	
	public CuentaBancaria(double saldo)
	{
		if (saldo < 0) saldo = 0;
		this.saldo = saldo;  
		this.historial = new ArrayList<Movimiento>();
		Movimiento m = new Movimiento(Tipo.DEPOSITO, saldo);
		this.historial.add(m);
	}
	
	public double getSaldo() {
        return saldo;
    }

	public List<Movimiento> getHistorial()
	{
		return historial;
	}
	
    public boolean depositar(double cantidad) {
        if (cantidad <= 0) return false;
        saldo += cantidad;
        Movimiento m = new Movimiento(Tipo.DEPOSITO, cantidad);
		this.historial.add(m);
        return true;
    }

    public boolean retirar(double cantidad) {
        if (cantidad <= 0 || cantidad > saldo) return false;
        saldo -= cantidad;
        Movimiento m = new Movimiento(Tipo.RETIRO, cantidad);
		this.historial.add(m);
        return true;
    }
}
