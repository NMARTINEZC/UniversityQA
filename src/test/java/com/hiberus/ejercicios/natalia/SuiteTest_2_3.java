package com.hiberus.ejercicios.natalia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SuiteTest_2_3 {
    public static WebDriver driver;

    public static WebDriverWait wait;

    @Before
    public void setUp() {
        //Paso0
        WebDriverManager.chromedriver().setup(); // Cargar Chromedriver

        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10, 500);
    }

    @Test
    public void loginCorrecto() {
        //URL
        String url = "https://www.saucedemo.com";

        //Ir a la página https://www.saucedemo.com
        driver.get(url);

        //Escribir el username standard_user
        WebElement inputUserName = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUserName.sendKeys("standard_user");

        //Escribir el password secret_sauce
        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.sendKeys("secret_sauce");

        //hago clic en el boton login
        WebElement botonLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        botonLogin.click();

        //validar url
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) System.out.println("Url correcta");
    }

    @Test
    public void loginInCorrecto() {
        //URL
        String url = "https://www.saucedemo.com";

        //Ir a la página https://www.saucedemo.com
        driver.get(url);

        //Escribir el username standard_user
        WebElement inputUserName = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUserName.sendKeys("standard_use");

        //Escribir el password secret_sauce
        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.sendKeys("secret_sauce");

        //hago clic en el boton login
        WebElement botonLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        botonLogin.click();
        //validar mensaje error
        String mensajeError = "Epic sadface: Username and password do not match any user in this service";
    }

    @Test
    public void validarNumeroResultadosInventario() {
        //URL
        String url = "https://www.saucedemo.com";

        //Ir a la página https://www.saucedemo.com
        driver.get(url);

        //Escribir el username standard_user
        WebElement inputUserName = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUserName.sendKeys("standard_user");

        //Escribir el password secret_sauce
        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.sendKeys("secret_sauce");

        //hago clic en el boton login
        WebElement botonLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        botonLogin.click();

        //Validar que el número de productos mostrados es igual a 6.
        List<WebElement> productsInventario = driver.findElements(By.xpath("//div[@class='inventory_list']/child::div"));
        assertEquals("La cantidad del inventario no es 6",6, productsInventario.size());

    }

    @Test
    public void incrementoValorCarrito() {
        //URL
        String url = "https://www.saucedemo.com";

        //Ir a la página https://www.saucedemo.com
        driver.get(url);

        //Escribir el username standard_user
        WebElement inputUserName = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUserName.sendKeys("standard_user");

        //Escribir el password secret_sauce
        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.sendKeys("secret_sauce");

        //hago clic en el boton login
        WebElement botonLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        botonLogin.click();

        //Añadir producto al carrito
        List<WebElement> productsInventario = driver.findElements(By.xpath("//div[@class='inventory_list']/child::div"));
        WebElement buttonAddToCart = productsInventario.get(0).findElement(By.xpath("//descendant::button[@id='add-to-cart-sauce-labs-backpack']"));
        buttonAddToCart.click();

        //Validar incremento carrito
        assertEquals("Valor incremento carrito diferente a 1", driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText(),"1");
    }

    @Test
    public void visibilidadBotoneliminarProductoCarrito() {
        //URL
        String url = "https://www.saucedemo.com";

        //Ir a la página https://www.saucedemo.com
        driver.get(url);

        //Escribir el username standard_user
        WebElement inputUserName = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUserName.sendKeys("standard_user");

        //Escribir el password secret_sauce
        WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
        inputPassword.sendKeys("secret_sauce");

        //hago clic en el boton login
        WebElement botonLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        botonLogin.click();

        //Añadir producto al carrito
        List<WebElement> productsInventario = driver.findElements(By.xpath("//div[@class='inventory_list']/child::div"));
        WebElement buttonAddToCart = productsInventario.get(0).findElement(By.xpath("//descendant::button[@id='add-to-cart-sauce-labs-backpack']"));
        buttonAddToCart.click();

        //hago clic en el carrito
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        //validar boton remove
        assertTrue("El botón remove no es visible", driver.findElement(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']")).isDisplayed());
    }

    @Test
    public void eliminarProductoCarrito() {
        //URL
        String url = "https://www.saucedemo.com";

        //Ir a la página https://www.saucedemo.com
        driver.get(url);

        //Escribir el username standard_user
        WebElement inputUserName = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUserName.sendKeys("standard_user");

        //Escribir el password secret_sauce
         WebElement inputPassword = driver.findElement(By.xpath("//input[@id='password']"));
         inputPassword.sendKeys("secret_sauce");

        //hago clic en el boton login
        WebElement botonLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        botonLogin.click();

        //Añadir el primer producto del inventario al carrito
        List<WebElement> productsInventario = driver.findElements(By.xpath("//div[@class='inventory_list']/child::div"));
        WebElement buttonAddToCart = productsInventario.get(0).findElement(By.xpath("//descendant::button[@id='add-to-cart-sauce-labs-backpack']"));
        buttonAddToCart.click();

        //hago clic en el carrito
        WebElement buttonCarrito = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        buttonCarrito.click();

        //clic boton remove
        WebElement buttonRemove = driver.findElement(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']"));
        buttonRemove.click();

        //declaro booleano
        Boolean isDisplayed;

        //validar que en el icono del carrito se ha eliminado el producto.
        try {
            isDisplayed = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).isDisplayed();
        }catch (NoSuchElementException e){
            isDisplayed = false;
        }

        //Assert de validación
        assertFalse("El webElement sigue siendo visible", isDisplayed);

    }

    @After
    public void tearDown() {
        //cerrar navegador
        driver.close();
    }
}
