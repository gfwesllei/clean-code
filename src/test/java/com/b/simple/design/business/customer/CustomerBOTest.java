package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;
import com.b.simple.design.model.customer.ProductImpl;
import com.b.simple.design.model.customer.ProductType;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerBOTest {

	private CustomerBO customerBO = new CustomerBOImpl();

	@Test
	public void testCustomerProductSum_TwoProductsSameCurrencies()
			throws DifferentCurrenciesException {

		AmountImpl amount1 = new AmountImpl(new BigDecimal("5.0"), Currency.EURO);
		AmountImpl amount2 = new AmountImpl(new BigDecimal("6.0"), Currency.EURO);
		Amount[] amounts = {amount1,amount2};

		List<Product> products = craeteProductsWithAmmount(amounts);

		Amount actual = customerBO.getCustomerProductsSum(products);

		AmountImpl expected = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);

		asserAmounts(actual, expected);
	}

	private void asserAmounts(Amount actual, Amount expected) {
		assertEquals(expected.getCurrency(), actual.getCurrency());
		assertEquals(expected.getValue(), actual.getValue());
	}

	private List<Product> craeteProductsWithAmmount(Amount[] amounts) {

		return Arrays.stream(amounts)
					  .map(amount->new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE,amount))
				      .collect(Collectors.toList());
	}

	@Test
	public void testCustomerProductSum_ThrowsDiferentCurrenciesException() {

		AmountImpl amount1 = new AmountImpl(new BigDecimal("5.0"), Currency.EURO);
		AmountImpl amount2 = new AmountImpl(new BigDecimal("6.0"), Currency.INDIAN_RUPEE);

		Amount[] amounts = {amount1,amount2};

		List<Product> products = craeteProductsWithAmmount(amounts);

		assertThrows(DifferentCurrenciesException.class,()->customerBO.getCustomerProductsSum(products));
	}

	@Test
	public void testCustomerProductSum_ZeroForEmptyList() throws DifferentCurrenciesException {

		List<Product> products = new ArrayList<>();

		Amount actual = customerBO.getCustomerProductsSum(products);

		Amount expected =  new AmountImpl(BigDecimal.ZERO,Currency.EURO);

		asserAmounts(expected, actual);
	}

	@Test
	public void shouldSumIntegersList(){
		Integer val1=5,val2=6,val3=4;
		List<Integer> listIntegers = Arrays.asList(val1,val2,val3);

		Integer expected =15;
		Integer actual = listIntegers.stream().reduce(0,Integer::sum);

		assertEquals(expected,actual);
	}

}