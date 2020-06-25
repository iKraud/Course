package ru.sushi.demo.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {

    public static final String BASKET_COOKIE_HANDLE = "basket";

    Map<String, String> items = new HashMap<>();

    public Basket(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(BASKET_COOKIE_HANDLE)) {
                    this.items = this.cookieToMap(cookie);
                    break;
                }
            }
        }
    }

    /**
     * Парсим куку в корзину
     *
     * @param cookie
     * @return
     */
    private Map<String, String> cookieToMap(Cookie cookie) {
        String str = cookie.getValue().substring(1, cookie.getValue().length() - 1);
        return Arrays.stream(str
                .split("_"))
                .map(entry -> entry.split("="))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
    }

    /**
     * @param id
     * @param quantity
     */
    public void add(Integer id, Integer quantity) {
        String currentQuantityStr = this.items.get(String.valueOf(id));
        if (currentQuantityStr != null) {
            quantity += Integer.valueOf(currentQuantityStr);
        }

        this.items.put(String.valueOf(id), String.valueOf(quantity));
    }

    /**
     * Парсим корзину в куку
     *
     * @return
     */
    @Override
    public String toString() {
        return this.items.toString().replaceAll(", ", "_");
    }

    /**
     * Возвращает кол-во товара
     *
     * @return
     */
    public Integer getQuantity() {
        return items.values().stream()
                .map(x -> Integer.valueOf(x))
                .reduce(0, Integer::sum);
    }

    /**
     *
     * @return
     */
    public Map<String, String> getItems() {
        return this.items;
    }
}
