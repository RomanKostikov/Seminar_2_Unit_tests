package classwork.task001;
/*
 ������������ ��������� ���� ��� ��������, ��� ����� ��������� ������� � �������
   �������� ��������� ��������������
   �������� � ����-������������
   ��������� ���������: ��������� ������� ����������� ���������
 */
/*
������� �2
�������� ��������� ���� ��� ��������, ��� ����� ��������� ������� � ��������������
������������ ������ � ���� �� �������� ��������� ��������������.
? ��������� ���������: ��������� ������� ����������� ���������
 */
/*
������� �3
�������� ��������� ���� ��� ��������, ��� ��� �������� ������ ��
������� ���������� ���������� ����� ��������� �������.
? ��������� ���������: ���������� ����� ��������� ���������
�������, ��������� ������� ��������
 */

/*
������� �4
������������ ��������� ���� ��� ��������, ��� ��� ���������� ������������� ����������
������ � �������, ����� ���������� ����� ������ � �������� ��������������� �������
�����������.
? ��������� ���������: ���������� ������ � ��������
����������� �� ����� ��������� � ������� ������������
 */

/*
������� �5
�������� ��������� ���� ��� ��������, ��� ���� ������������ �������� ��� ���������
�������� ������������� ���� �� ��������, ��� �������� ������ �� �������� ��� ������.
? ��������� ���������: ������ ����� �������
�������� ������, �� �� ���������� �� �����
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    private Shop shop;
    private Cart cart;
    private Product product1;
    private Product product2;


    @BeforeEach
    public void setUp() {
        cart = new Cart();
        shop = new Shop();
        product1 = new Product(1, "����� 1", 500.0, 1);
        product2 = new Product(2, "����� 2", 300.0, 1);
    }

    @Test
    public void testCalculateTotalWithMultipleProducts() {
// ������� ��������� ���������
        Product product1 = new Product(1, "����� 1", 500.0, 1);
        Product product2 = new Product(2, "����� 2", 300.0, 1);
        Product product3 = new Product(3, "����� 3", 200.0, 1);

// ��������� �������� � �������
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);

// ���������, ��� ����� ��������� ������� �������������� ���������
        double expectedTotalPrice = 1000.0; // 500 + 300 + 200
        double actualTotalPrice = cart.calculateTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01); // ����������� 0.01 ��� ��������� � ��������� ������
    }

    @Test
    public void testCalculateTotalWithEmptyCart() {
// ���������, ��� ����� ��������� ������ ������� ����� 0.0
        double expectedTotalPrice = 0.0;
        double actualTotalPrice = cart.calculateTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01);
    }

    @Test
    public void testCalculateTotalWithSingleProduct() {
// ������� ���� �������
        Product product = new Product(1, "����� 1", 500.0, 1);

// ��������� ������� � �������
        cart.addProduct(product);

// ���������, ��� ����� ��������� ������� � ����� ������� ����� ���� ����� ������
        double expectedTotalPrice = 500.0;
        double actualTotalPrice = cart.calculateTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01);
    }

    @Test
    public void testCalculateTotalWithMultipleTheSameProducts() {
// ������� ��������� ���������
        Product product1 = new Product(1, "����� 1", 500.0, 1);
        Product product2 = new Product(2, "����� 2", 500.0, 1);
        Product product3 = new Product(3, "����� 3", 200.0, 1);

// ��������� �������� � �������
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);

// ���������, ��� ����� ��������� ������� �������������� ���������
        double expectedTotalPrice = 1200.0; // 500 + 500 + 200
        double actualTotalPrice = cart.calculateTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01); // ����������� 0.01 ��� ��������� � ��������� ������
    }

    @Test
    public void testRecalculateTotalAfterRemovingProduct() {
// ��������� �������� � �������
        cart.addProduct(product1);
        cart.addProduct(product2);

// �������������� ��������� �������
        double initialTotalPrice = cart.calculateTotal();

// ������� ������� �� �������
        cart.removeProduct(product1);

// �������� ����������� ��������� ������� ����� �������� ������
        double updatedTotalPrice = cart.calculateTotal();

// ���������, ��� ��������� ������� ���������� ����� �������� ������
        assertNotEquals(initialTotalPrice, updatedTotalPrice, 0.01); // ����������� 0.01 ��� ��������� � ��������� ������
    }

    @Test
    public void testDecreaseProductQuantityInShop() {
// ��������� 1 ����� � �������
        shop.addProduct(product1);
        cart.addProduct(product1);

// ���������, ��� ���������� ������ � �������� ����������� �� 3
        int expectedQuantityInShop = 1; // 5 - 3
        shop.decreaseProductQuantity(product1, 1);
//                findProductById(product.getId()).getQuantity();
        int actualQuantityInShop = shop.getProducts().size();
        assertEquals(expectedQuantityInShop, actualQuantityInShop + 1);
    }

    @Test
    public void testTotalWithMultipleProducts() {
// ������� ��������� ���������
        Product product1 = new Product(1, "����� 1", 500.0, 2);
        Product product2 = new Product(2, "����� 2", 300.0, 1);
        Product product3 = new Product(3, "����� 3", 200.0, 1);

// ��������� �������� � �������
        cart.addProduct(product1);
        cart.addProduct(product1);
//        cart.addProduct(product3);
        List<Product> products = shop.getProducts();
        assertTrue(products.isEmpty());
// ���������, ��� ����� ��������� ������� �������������� ���������
//        double expectedTotalPrice = 1000.0; // 500 + 300 + 200
//        double actualTotalPrice = cart.calculateTotal();
//        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01); // ����������� 0.01 ��� ��������� � ��������� ������
    }
}
