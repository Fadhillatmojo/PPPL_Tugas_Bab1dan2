package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WalletTest {
    static List<Wallet> wallets;
    static List<Card> listCard = new ArrayList<>();

    @BeforeAll
    static void initClass(){
        wallets = new ArrayList<>();
    }

    @BeforeEach
    void initMethod(){
        // Penambahan elemen ke dalam listCard
        listCard.add(new Card("fadhil atmojo", "8787865", "BCA"));
        listCard.add(new Card("Raisa", "08787899965", "Madiri"));

        // penambahan wallet ke dalam
        Wallet wallet1 = new Wallet("Fadhilloh", listCard, 200000.0);
        Wallet wallet2 = new Wallet("Raisa", 75000.0);
        wallets.add(wallet1);
        wallets.add(wallet2);
    }
    @AfterEach
    void closeMethod(){
        wallets.clear();
        listCard.clear();
    }
    @AfterAll
    static void closeClass(){
        wallets = null;
        listCard = null;
    }

    // withdraw testing method
    @Test
    void testWithdrawNegative(){
        // withdraw wallet 1
        Double actual1 = wallets.get(0).withdraw(-10000.0);
        // withdraw wallet 1
        Double actual2 = wallets.get(1).withdraw(-10000.0);

        Double expected = 0.0;

        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void testWithdrawTooMuch(){
        // withdraw wallet 1
        Double actual1 = wallets.get(0).withdraw(210000.0);
        Double expected1 = 200000.0;

        // withdraw wallet 2
        Double actual2 = wallets.get(1).withdraw(76000.0);
        Double expected2 = 75000.0;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void testWithdrawPassed(){
        // withdraw wallet 1
        Double actual1 = wallets.get(0).withdraw(100000.0);
        Double expected1 = 100000.0;

        // withdraw wallet 2
        Double actual2 = wallets.get(1).withdraw(70000.0);
        Double expected2 = 70000.0;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
    // end withdraw testing method

    // start deposit testing
    @Test
    void testDeposit() {
        // deposit wallet 1
        Double actual1 = wallets.get(0).deposit(33000.0);
        Double expected1 = 233000.0;
        // deposit wallet 2
        Double actual2 = wallets.get(1).deposit(12000.0);
        Double expected2 = 87000.0;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
    // end deposit testing

    // start add card
    @Test
    void testAddCard() {
        // menambahkan suatu card ke dalam wallet 1
        int accountNumber1 = 33376768;
        String bank1 = "Mandiri";
        wallets.get(0).addCard(bank1, accountNumber1);

        // handle nemuin cardnya atau gak di wallet 1
        boolean isFind1 = false;
        for (Card card: wallets.get(0).getCards()) {
            if (card.getNoRek().equals(Integer.toString(accountNumber1))) {
                isFind1 = true;
                System.out.println("Berhasil ditambahkan kartu: " + card.toString());
            }
        }

        // menambahkan suatu card ke dalam wallet 2
        int accountNumber2 = 373781581;
        String bank2 = "BNI";
        wallets.get(1).addCard(bank2, accountNumber2);

        // handle nemuin cardnya atau gak di wallet 1
        boolean isFind2 = false;
        for (Card card: wallets.get(1).getCards()) {
            if (card.getNoRek().equals(Integer.toString(accountNumber2))) {
                isFind2 = true;
                System.out.println("Berhasil ditambahkan kartu: " + card.toString());
            }
        }

        assertTrue(isFind1);
        assertTrue(isFind2);
    }
    // end add card

    // start remove card
    @Test
    void testRemoveCard() {
        // menghapus kartu dari dalam wallet 1
        int accountNumber1 = 8787865;
        wallets.get(0).removeCard(accountNumber1);

        // handle nemuin cardnya atau gak di wallet 1
        boolean isUnDeleted1 = false;
        for (Card card: wallets.get(0).getCards()) {
            if (card.getNoRek().equals(Integer.toString(accountNumber1))) {
                isUnDeleted1 = true;
                System.out.println("Gagal Dihapus");
            }
        }

        assertFalse(isUnDeleted1);
    }
    // end remove card
}
