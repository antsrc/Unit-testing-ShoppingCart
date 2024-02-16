package ejerciciosUnitarias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ShoppingCartTest {

	ShoppingCart carro;

	@BeforeEach
	public void init() {
		carro = new ShoppingCart();
		carro.addItem(new Product("Brick de leche", 0.9)); // Factor común
	}

	@Test
	public void testGetItemCount() {
		assertThat("El carro debería tener un elemento", carro.getItemCount(), is(1));
		carro = new ShoppingCart();
		assertThat("El carro debería crearse sin elementos", carro.getItemCount(), is(0));
	}
	
	@Test
	public void testEmpty() {
		carro.empty();
		assertThat("El carro debería quedarse vacío", carro.getItemCount(), is(0));
	}
	
	@Test
	public void testAddItem() {
		carro.addItem(new Product("Tomate frito", 1.2));
		assertThat("El carro debería tener dos elementos", carro.getItemCount(), is(2));
	}

	@Test
	public void testGetBalance() {
		double balancePrevio = carro.getBalance();
		double precioTomate = 1.2;
		carro.addItem(new Product("Tomate frito", precioTomate));
		assertThat(carro.getBalance(), is(balancePrevio + precioTomate));
	}

	@Test
	public void testRemoveItem() {
		try {
			carro.removeItem(new Product("Brick de leche", 0.9));
		} catch (ProductNotFoundException e) {
			System.err.println("Error al intentar encontrar el producto: " + e.getMessage());
		}
		assertThat(carro.getItemCount(), is(0));
	}

	@Test
	public void testProductNotFoundException() {
		try {
			carro.removeItem(null);
			fail("Debería lanzarse la excepción");
		} catch (ProductNotFoundException e) {
			System.err.println("Error al intentar encontrar el producto: " + e.getMessage());
		}
	}

}
