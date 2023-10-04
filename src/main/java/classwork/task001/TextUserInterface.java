package classwork.task001;

import java.util.List;
import java.util.Scanner;

public class TextUserInterface {
    private Shop shop;
    private Cart cart;
    private Scanner scanner;

    public TextUserInterface(Shop shop, Cart cart) {
        this.shop = shop;
        this.cart = cart;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("1. Просмотреть продукты");
            System.out.println("2. Добавить продукт в корзину");
            System.out.println("3. Удалить продукт из корзины");
            System.out.println("4. Посмотреть корзину");
            System.out.println("5. Рассчитать общую стоимость корзины");
            System.out.println("0. Выйти");

            System.out.print("Выберите действие: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    removeProductFromCart();
                    break;
                case 4:
                    displayCart();
                    break;
                case 5:
                    calculateTotal();
                    break;
                case 0:
                    System.out.println("Выход.");
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        } while (choice != 0);
    }

    public void displayProducts() {
        System.out.println("Список продуктов в магазине:");
        List<Product> products = shop.getProducts();
        for (Product product : products) {
            System.out.println(product.getId() + ". " + product.getName() + " - " + product.getPrice() + " руб.");
        }
    }

    public void addProductToCart() {
// Логика добавления продукта в корзину
    }

    public void removeProductFromCart() {
// Логика удаления продукта из корзины
    }

    public void displayCart() {
// Отображение содержимого корзины
    }

    public void calculateTotal() {
        double total = cart.calculateTotal();
        System.out.println("Общая стоимость корзины: " + total + " руб.");
    }

    public static void main(String[] args) {
// Создание экземпляров магазина и корзины
        Shop shop = new Shop();
        Cart cart = new Cart();

// Создание интерфейса и запуск меню
        TextUserInterface ui = new TextUserInterface(shop, cart);
        ui.displayMenu();
    }

}
