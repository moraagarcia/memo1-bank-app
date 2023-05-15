package com.aninfo.integration.cucumber;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.service.AccountService;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountOperationsTest extends AccountIntegrationServiceTest {

    @Autowired
    private AccountService accountService;

    private Account account;
    private InsufficientFundsException ife;
    private DepositNegativeSumException dnse;

    @Before
    public void setup() {
        System.out.println("Before any test execution");
    }

    @Given("^Account with a balance of (\\d+)$")
    public void account_with_a_balance_of(int balance)  {
        account = createAccount(Double.valueOf(balance));
    }

    @When("^Trying to withdraw (\\d+)$")
    public void trying_to_withdraw(int sum) {
        try {
            withdraw(account, Double.valueOf(sum));
        } catch (InsufficientFundsException ife) {
            this.ife = ife;
        }
    }

    @When("^Trying to deposit (.*)$")
    public void trying_to_deposit(int sum) {
        try {
            deposit(account, Double.valueOf(sum));
        } catch (DepositNegativeSumException dnse) {
            this.dnse = dnse;
        }
    }

    @Then("^Account balance should be (\\d+)$")
    public void account_balance_should_be(int balance) {
        Account accountToCheck = accountService.findById(account.getCbu()).get();
        assertEquals(Double.valueOf(balance), accountToCheck.getBalance());
    }

    @Then("^Operation should be denied due to insufficient funds$")
    public void operation_should_be_denied_due_to_insufficient_funds() {
        assertNotNull(ife);
    }

    @Then("^Operation should be denied due to negative sum$")
    public void operation_should_be_denied_due_to_negative_sum() {
        assertNotNull(dnse);
    }

    @And("^Account balance should remain (\\d+)$")
    public void account_balance_should_remain(int balance) {
        Account accountToCheck = accountService.findById(account.getCbu()).get();
        assertEquals(Double.valueOf(balance), accountToCheck.getBalance());
    }

    @After
    public void tearDown() {
        System.out.println("After all test execution");
    }
}
