package com.emmanueltorty.walletapp.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MainControllerTest {

	@Test
	void testHomeShouldReturnWelcomeString() {
		
		MainController underTest = new MainController();
		assertThat(underTest.home()).isEqualTo("Welcome to Wallet APP APIs");
	}

}
