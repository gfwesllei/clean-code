package com.b.simple.design.business.customer;

import java.math.BigDecimal;
import java.util.List;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;

public class CustomerBOImpl implements CustomerBO {

	@Override
	public Amount getCustomerProductsSum(List<Product> products)
			throws DifferentCurrenciesException {

		if (products.isEmpty())
			return new AmountImpl(BigDecimal.ZERO, Currency.EURO);

		if(!doAllProductHaveSomeCurrence(products)){
			throw new DifferentCurrenciesException();
		}

		return sumAmountProducts(products);
	}

	private AmountImpl sumAmountProducts(List<Product> products) {

		Currency firstProductCurrency = products.get(0).getAmount()
				.getCurrency();

		BigDecimal totalAmount = products.stream()
			.map(product -> product.getAmount().getValue())
			.reduce(BigDecimal.ZERO,BigDecimal::add);

		return new AmountImpl(totalAmount, firstProductCurrency);
	}

	private boolean doAllProductHaveSomeCurrence(List<Product> products) {

		Currency firstProductCurrency = products.get(0).getAmount()
				.getCurrency();

		return products.stream()
				.map(product -> product.getAmount().getCurrency())
				.allMatch(currency -> currency.equals(firstProductCurrency));
	}
}