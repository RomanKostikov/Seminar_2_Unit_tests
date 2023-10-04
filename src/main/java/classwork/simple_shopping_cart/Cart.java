package classwork.simple_shopping_cart;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    public List<Product> cartItems = new ArrayList<>(); // Корзина с продуктами

    // При создании корзины нужно передать магазин
    Shop shop;

    private Double totalPrice;

    public Cart(Shop shop) {
        this.shop = shop;
    }

    // Метод для добавления продукта в корзину по его id
    public void addProductToCartByID(int id) {
        Product product = getProductByProductID(id);

        addToCart(product);

        recalculate();
    }

    public void recalculate() { // Метод пересчитывает сумму покупки
        totalPrice = 0.00d;
        for (Product p : cartItems) {
            totalPrice += p.getPrice() * p.getQuantity();
        }
    }

    // Чтобы положить продукт в корзину, его нужно сначала найти в магазине
    Product getProductByProductID(int id) {
        Product product = new Product();

        List<Product> products = shop.getProductsShop();
        for (Product prod : products) {
            if (prod.getId() == id) {
                product = prod;
                break;
            }
        }
        if (id > shop.getProductsShop().size() || id < 0) {
            try {
                throw new NoSuchFieldException("Не найден продукт с id: " + id);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException("Не найден продукт с id: " + id);
            }
        }
        return product;
    }

    private void addToCart(Product product) {
        Product productInCart = new Product(product.getId(), product.getName(), product.getPrice(), 0);
        Product productInShop = shop.getProductsShop().get(product.getId() - 1);

        if (productInShop.getQuantity() == 0) {
            System.out.println("Этого товара нет в наличии");
            return;
        }

        // Изменяем кол-во в корзине +1
        if (hasContainProduct(productInCart)) {
            getContainProduct(productInCart).setQuantity(getContainProduct(productInCart).getQuantity() + 1);
        } else {
            productInCart.setQuantity(1);
            cartItems.add(productInCart);
        }
        recalculate();
        // Изменяем кол-во в магазине -1
        shop.getProductsShop().get(product.getId() - 1).setQuantity(productInShop.getQuantity() - 1);
    }


    private boolean hasContainProduct(Product product) {
        for (Product cartItem : cartItems) {
            if (Objects.equals(cartItem.getId(), product.getId())) return true;
        }
        return false;
    }

    private boolean hasContainProductID(int id) {
        for (Product cartItem : cartItems) {
            if (Objects.equals(cartItem.getId(), id)) return true;
        }
        return false;
    }

    // Поиск продукта в корзине, если он был уже добавлен ранее
    private Product getContainProduct(Product product) {
        for (Product cartItem : cartItems) {
            if (Objects.equals(cartItem.getId(), product.getId())) return cartItem;
        }
        return null;
    }

    public void removeProductByID(int id) {

        if (!hasContainProductID(id)) {
            if (!hasContainProductID(id)) {
                try {
                    throw new NoSuchFieldException("В корзине не найден продукт с id: " + id);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException("В корзине не найден продукт с id: " + id);
                }
            }
        }

        // Изменяем кол-во в корзине -1
        Product prod = getProductByProductID(id);
        if (hasContainProduct(prod) && getContainProduct(prod).getQuantity() > 1) {
            getContainProduct(prod).setQuantity(getContainProduct(prod).getQuantity() - 1);
        } else if (hasContainProduct(prod) && getContainProduct(prod).getQuantity() == 1) {
            cartItems.remove(getContainProduct(prod));
        }


        try {
            Thread.sleep(70);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        recalculate();

        // Изменяем кол-во в магазине +1
        Product productInShop = shop.getProductsShop().get(id - 1);
        shop.getProductsShop().get(id - 1).setQuantity(productInShop.getQuantity() + 1);

        //  if (productInShop.getQuantity() == 0) {
        //      shop.getProductsShop().remove(productInShop);
        //   }
    }

    void printCartItems() {
        String format = "%1$-3s| %2$-20s| %3$-9s| %4$-3s\n";
        System.out.println("Сейчас у вас в корзине:");
        System.out.format(format, "ID", "Название", "Цена, р.", "Кол-во в корзине, шт.");
        for (Product prod : cartItems) {
            System.out.format(format, prod.getId(), prod.getName(), prod.getPrice(), prod.getQuantity());
        }
        System.out.println("Общая стоимость корзины: " + getTotalPrice() + " р.");
        System.out.println();
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}