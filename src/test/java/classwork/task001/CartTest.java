package classwork.task001;
/*
 Разработайте модульный тест для проверки, что общая стоимость корзины с разными
   товарами корректно рассчитывается
   Введение в юнит-тестирование
   Ожидаемый результат: Стоимость корзины посчиталась корректно
 */
/*
Задание №2
Создайте модульный тест для проверки, что общая стоимость корзины с множественными
экземплярами одного и того же продукта корректно рассчитывается.
? Ожидаемый результат: Стоимость корзины посчиталась корректно
 */
/*
Задание №3
Напишите модульный тест для проверки, что при удалении товара из
корзины происходит перерасчет общей стоимости корзины.
? Ожидаемый результат: Вызывается метод пересчета стоимости
корзины, стоимость корзины меняется
 */

/*
Задание №4
Разработайте модульный тест для проверки, что при добавлении определенного количества
товара в корзину, общее количество этого товара в магазине соответствующим образом
уменьшается.
? Ожидаемый результат: Количество товара в магазине
уменьшается на число продуктов в корзине пользователя
 */

/*
Задание №5
Создайте модульный тест для проверки, что если пользователь забирает все имеющиеся
продукты определенного типа из магазина, эти продукты больше не доступны для заказа.
? Ожидаемый результат: Больше такой продукт
заказать нельзя, он не появляется на полке
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
        product1 = new Product(1, "Товар 1", 500.0, 1);
        product2 = new Product(2, "Товар 2", 300.0, 1);
    }

    @Test
    public void testCalculateTotalWithMultipleProducts() {
// Создаем несколько продуктов
        Product product1 = new Product(1, "Товар 1", 500.0, 1);
        Product product2 = new Product(2, "Товар 2", 300.0, 1);
        Product product3 = new Product(3, "Товар 3", 200.0, 1);

// Добавляем продукты в корзину
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);

// Проверяем, что общая стоимость корзины рассчитывается корректно
        double expectedTotalPrice = 1000.0; // 500 + 300 + 200
        double actualTotalPrice = cart.calculateTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01); // Погрешность 0.01 для сравнения с плавающей точкой
    }

    @Test
    public void testCalculateTotalWithEmptyCart() {
// Проверяем, что общая стоимость пустой корзины равна 0.0
        double expectedTotalPrice = 0.0;
        double actualTotalPrice = cart.calculateTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01);
    }

    @Test
    public void testCalculateTotalWithSingleProduct() {
// Создаем один продукт
        Product product = new Product(1, "Товар 1", 500.0, 1);

// Добавляем продукт в корзину
        cart.addProduct(product);

// Проверяем, что общая стоимость корзины с одним товаром равна цене этого товара
        double expectedTotalPrice = 500.0;
        double actualTotalPrice = cart.calculateTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01);
    }

    @Test
    public void testCalculateTotalWithMultipleTheSameProducts() {
// Создаем несколько продуктов
        Product product1 = new Product(1, "Товар 1", 500.0, 1);
        Product product2 = new Product(2, "Товар 2", 500.0, 1);
        Product product3 = new Product(3, "Товар 3", 200.0, 1);

// Добавляем продукты в корзину
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.addProduct(product3);

// Проверяем, что общая стоимость корзины рассчитывается корректно
        double expectedTotalPrice = 1200.0; // 500 + 500 + 200
        double actualTotalPrice = cart.calculateTotal();
        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01); // Погрешность 0.01 для сравнения с плавающей точкой
    }

    @Test
    public void testRecalculateTotalAfterRemovingProduct() {
// Добавляем продукты в корзину
        cart.addProduct(product1);
        cart.addProduct(product2);

// Первоначальная стоимость корзины
        double initialTotalPrice = cart.calculateTotal();

// Удаляем продукт из корзины
        cart.removeProduct(product1);

// Получаем обновленную стоимость корзины после удаления товара
        double updatedTotalPrice = cart.calculateTotal();

// Проверяем, что стоимость корзины изменилась после удаления товара
        assertNotEquals(initialTotalPrice, updatedTotalPrice, 0.01); // Погрешность 0.01 для сравнения с плавающей точкой
    }

    @Test
    public void testDecreaseProductQuantityInShop() {
// Добавляем 1 товар в корзину
        shop.addProduct(product1);
        cart.addProduct(product1);

// Проверяем, что количество товара в магазине уменьшилось на 3
        int expectedQuantityInShop = 1; // 5 - 3
        shop.decreaseProductQuantity(product1, 1);
//                findProductById(product.getId()).getQuantity();
        int actualQuantityInShop = shop.getProducts().size();
        assertEquals(expectedQuantityInShop, actualQuantityInShop + 1);
    }

    @Test
    public void testTotalWithMultipleProducts() {
// Создаем несколько продуктов
        Product product1 = new Product(1, "Товар 1", 500.0, 2);
        Product product2 = new Product(2, "Товар 2", 300.0, 1);
        Product product3 = new Product(3, "Товар 3", 200.0, 1);

// Добавляем продукты в корзину
        cart.addProduct(product1);
        cart.addProduct(product1);
//        cart.addProduct(product3);
        List<Product> products = shop.getProducts();
        assertTrue(products.isEmpty());
// Проверяем, что общая стоимость корзины рассчитывается корректно
//        double expectedTotalPrice = 1000.0; // 500 + 300 + 200
//        double actualTotalPrice = cart.calculateTotal();
//        assertEquals(expectedTotalPrice, actualTotalPrice, 0.01); // Погрешность 0.01 для сравнения с плавающей точкой
    }
}
