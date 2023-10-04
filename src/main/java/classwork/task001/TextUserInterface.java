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
            System.out.println("1. ����������� ��������");
            System.out.println("2. �������� ������� � �������");
            System.out.println("3. ������� ������� �� �������");
            System.out.println("4. ���������� �������");
            System.out.println("5. ���������� ����� ��������� �������");
            System.out.println("0. �����");

            System.out.print("�������� ��������: ");
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
                    System.out.println("�����.");
                    break;
                default:
                    System.out.println("�������� �����.");
                    break;
            }
        } while (choice != 0);
    }

    public void displayProducts() {
        System.out.println("������ ��������� � ��������:");
        List<Product> products = shop.getProducts();
        for (Product product : products) {
            System.out.println(product.getId() + ". " + product.getName() + " - " + product.getPrice() + " ���.");
        }
    }

    public void addProductToCart() {
// ������ ���������� �������� � �������
    }

    public void removeProductFromCart() {
// ������ �������� �������� �� �������
    }

    public void displayCart() {
// ����������� ����������� �������
    }

    public void calculateTotal() {
        double total = cart.calculateTotal();
        System.out.println("����� ��������� �������: " + total + " ���.");
    }

    public static void main(String[] args) {
// �������� ����������� �������� � �������
        Shop shop = new Shop();
        Cart cart = new Cart();

// �������� ���������� � ������ ����
        TextUserInterface ui = new TextUserInterface(shop, cart);
        ui.displayMenu();
    }

}
