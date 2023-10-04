package classwork.task001;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    public List<Product> items = new ArrayList<>(); // Корзина с продуктами

    // При создании корзины нужно передать магазин
    Shop shop;
    private Double totalPrice;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean containsProduct(Product product) {
        return items.contains(product);
    }


    private boolean hasContainProduct(Product product) {
        for (Product cartItem : items) {
            if (Objects.equals(cartItem.getId(), product.getId())) return true;
        }
        return false;
    }

    private boolean hasContainProductID(int id) {
        for (Product cartItem : items) {
            if (Objects.equals(cartItem.getId(), id)) return true;
        }
        return false;
    }

    // Поиск продукта в корзине, если он был уже добавлен ранее
    private Product getContainProduct(Product product) {
        for (Product cartItem : items) {
            if (Objects.equals(cartItem.getId(), product.getId())) return cartItem;
        }
        return null;
    }

    public void recalculate() { // Метод пересчитывает сумму покупки
        totalPrice = 0.00d;
        for (Product p : items) {
            totalPrice += p.getPrice() * p.getQuantity();
        }
    }
}
