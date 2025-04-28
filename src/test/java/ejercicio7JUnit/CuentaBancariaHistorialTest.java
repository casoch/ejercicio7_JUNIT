package ejercicio7JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ejercicio7JUnit.Movimiento.Tipo;

public class CuentaBancariaHistorialTest {
	CuentaBancaria cb;
	
	@BeforeEach
	void init()
	{
		cb = new CuentaBancaria(100);
	}
	
	/**
	 * Comprobar que el movimiento del ingreso inicial se guarda bien
	 */
	@Test
	void historial_IngresoInicial()
	{
		List<Movimiento> historial = cb.getHistorial();
		assertEquals(historial.size(), 1);
		Movimiento m = historial.get(0);
		assertEquals(m.getTipo(), Tipo.DEPOSITO);
		assertEquals(m.getCantidad(), 100);
	}
	
	/**
	 * Comprobar que se añade correctamente un deposito a una cuenta que
	 * ya existe con un depósito inicial
	 */
	@Test
	void historial_AddDepositoCorrecto()
	{
		cb.depositar(25);
		List<Movimiento> historial = cb.getHistorial();
		assertEquals(2, historial.size());
		Movimiento mov = historial.get(1);
		assertEquals(mov.getTipo(), Tipo.DEPOSITO);
		assertEquals(mov.getCantidad(), 25);
	}
	
	/**
	 * Comprobar que no se añade un movimiento si el deposito es negativo
	 */
	@Test
	void historial_AddDepositoIncorrecto()
	{
		cb.depositar(-25);
		List<Movimiento> historial = cb.getHistorial();
		assertEquals(1, historial.size());
	}
	
	/**
	 * Comprobar que se puede hacer un retiro de una cantidad correcta y 
	 * se añade a los movimientos
	 */
	@Test
	void historial_AddRetiroCorrecto()
	{
		cb.retirar(25);
		List<Movimiento> historial = cb.getHistorial();
		assertEquals(2, historial.size());
		Movimiento mov = historial.get(1);
		assertEquals(mov.getTipo(), Tipo.RETIRO);
		assertEquals(mov.getCantidad(), 25);
	}
	
	/**
	 * Comprobar que no se puede hacer un retiro de una cantidad negativa/incorrecta y 
	 * no se añade a los movimientos
	 */
	@Test
	void historial_AddRetiroIncorrecto()
	{
		cb.retirar(110);
		assertEquals(1, cb.getHistorial().size());
		cb.retirar(-50);
		assertEquals(1, cb.getHistorial().size());
	}
}
