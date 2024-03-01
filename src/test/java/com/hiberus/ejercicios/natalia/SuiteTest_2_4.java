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

public class SuiteTest_2_4 {

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
        String url = "https://www.saucedemo.com/";

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
        assertEquals("La url no es la esperada", url+"inventory.html", driver.getCurrentUrl());
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

        String mensajeErrorWeb = "";

        //validar mensaje error
        boolean isDisplayed = true;
        try {
            mensajeErrorWeb = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        } catch (NoSuchElementException e){
            isDisplayed = false;
        }

        String mensajeError = "Epic sadface: Username and password do not match any user in this service";

        assertTrue("El mensaje de error no es visible", isDisplayed);
        assertEquals("El mensaje de error no es el esperado",mensajeError, mensajeErrorWeb);
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
    public void validartSauceLabInventario() {
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

        boolean isDispalyed = true;

        //validar que el producto aparece en el inventario
        try {
            WebElement productoSauceLab = driver.findElement(By.xpath("//div[@class='inventory_list']/descendant::div[@class='inventory_item_name ' and text() = 'Sauce Labs Bolt T-Shirt']"));
            productoSauceLab.isDisplayed();
        }catch (NoSuchElementException e){
            isDispalyed = false;
        }

        assertTrue("no aparece el producto Sauce lab en el inventario", isDispalyed);
    }

    @Test
    public void añadirPorductSauceLabCarrito() {
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

        //agregar al carrito el producto Sauce Labs
        WebElement botonAgregarAlCarrito = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        botonAgregarAlCarrito.click();

        //hago clic en el carrito
        WebElement botonCarrito = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        botonCarrito.click();

        //crear boleano isdisplayed
        boolean isDisplayed = true;

        //validar que el producto este en el carrito
        try{
            //comprobar visibilidad del artículo
            WebElement visibilidadProducto = driver.findElement(By.xpath("//a[@id='item_1_title_link']"));
            visibilidadProducto.isDisplayed();
        }catch (NoSuchElementException e){
            isDisplayed = false;
        }

        assertTrue("no se ha encontrado el elemento Sauce Lab en el carrito",isDisplayed);


    }

    @Test
    public void  eliminarDesdeInventario (){
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

        //agregar al carrito el producto Sauce Labs
        WebElement botonAgregarAlCarrito = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        botonAgregarAlCarrito.click();

        //eliminar el producto
        WebElement botonEliminar = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bolt-t-shirt']"));
        botonEliminar.click();

        boolean isDisplayed = true;
        //validar que en el icono del carrito se ha eliminado el producto

        try {
            //comprobar visibilidad del carrito si se ha quitado el producto añadido
            WebElement numeroCarrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
            numeroCarrito.isDisplayed();
        }
        catch (NoSuchElementException e){
            isDisplayed = false;
        }
        assertFalse("el icono del carrito sigue siendo visible", isDisplayed);
    }

    @Test
    public void agregar3ProductosAzar() {
        //Instancia de la clase random
        Random random = new Random();
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
        for (int i = 0; i < 3; i++) {
            productsInventario.get(random.nextInt(6)).findElement(By.xpath("/descendant::button")).click();
        }

        //hago clic en el carrito
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

    }

    @Test
    public void ordenarinventarioZtoA() {
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

        //guardo en menufiltro el web element de los filtros
        WebElement menuFiltro = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        //convierto el menufiltro en un select
        Select select = new Select(menuFiltro);
        //selecciono del select si es visible la opcion ztoa
        select.selectByVisibleText("Name (Z to A)");
        //creo una lista para guardar el listado de nombres
        List<WebElement> nombres = driver.findElements(By.xpath("//div[@class='inventory_list']/descendant::div[@class='inventory_item_name ' and text()]"));
        //creo listas strings para guardar nombres y posteriormente compararlas
        List<String> nombresStringOrdenInverso = new ArrayList<>();
        List<String> nombresObtenidos = new ArrayList<>();

        //hago un bucle for para recoger e imprimir los nombres de las listas
        for (WebElement nombre : nombres){
            nombresStringOrdenInverso.add(nombre.getText());
            nombresObtenidos.add(nombre.getText());
        }

        //ordeno la lista obtenida en sentido inverso
        Collections.sort(nombresStringOrdenInverso);
        Collections.reverse(nombresStringOrdenInverso);

        //compara la lista en orden invertido con la obtenida
        assertEquals("El orden de la lista no es Z to A", nombresObtenidos,nombresStringOrdenInverso);
    }

    @Test
    public void ordenarinventarioporPrecioMenoraMayor() {
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

        //guardo en menufiltro el web element de los filtros
        WebElement menuFiltro = driver.findElement(By.xpath("//select[@class='product_sort_container']"));

        //convierto el menufiltro en un select
        Select selectMenuFiltro = new Select(menuFiltro);

        //selecciono del select si es visible la opcion precio menor a mayor
        selectMenuFiltro.selectByVisibleText("Price (low to high)");

        //creo una lista para guardar el listado de precios
        List<WebElement> precios = driver.findElements(By.xpath("//div[@class='inventory_list']/descendant::div[@class='inventory_item_price' and text()]"));

        //creo lista para añadir los precios
        List<Double> preciosLista = new ArrayList<>();

        //hago un bucle for para recoger e imprimir los precios de las listas
        for (WebElement precio : precios){
            String text = precio.getText();
            //uso de regex para remplazar el simbolo dolar por nada
            text = text.replaceAll("[^\\d.]","");
            //imprimo la lista por consola
            System.out.println(text);
            //convierto el precio de texto a numero
            double price = Double.parseDouble(text);
            //añado a la lista el precio
            preciosLista.add(price);
        }

        //validar el orden de menor a mayor precio
        boolean isSorted = true;
        for (int i=1; i<preciosLista.size(); i++){
            if (preciosLista.get(i - 1)>preciosLista.get(i)){
                isSorted=false;
                break;
            }
        }
        assertTrue("La lista no esta ordenada",isSorted);
    }

    @Test
    public void eliminarDesdeCarrito(){
        //Instancia de la clase random
        Random random = new Random();

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


        for (int i = 0; i < 2; i++) {
            //agregar al carrito 2productos al azar
            //Añadir producto al carrito
            List<WebElement> productsInventarioAdd = driver.findElements(By.xpath("//div[@class='inventory_list']/child::div/descendant::button[text() = 'Add to cart']"));
            productsInventarioAdd.get(random.nextInt(5)).click();
        }
        //ir al carrito
        WebElement carrito = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        carrito.click();

        //eliminar uno de los productos
        List<WebElement> productsInventarioRemove = driver.findElements(By.xpath("//button[text() = 'Remove']"));
        productsInventarioRemove.get(random.nextInt(2)).click();

        //Validar que el producto eliminado, no aparece en el carrito.
        WebElement numeroCarrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        String cantidadCarrito = numeroCarrito.getText();
        assertEquals("La cantidad del carrito es diferente a 1", cantidadCarrito, "1");

    }

    @Test
    public void comprobarPrecionFinalCheckout(){

        //Instancia de la clase random
        Random random = new Random();

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
        for (int i = 0; i < 1; i++) {
            productsInventario.get(random.nextInt(6)).findElement(By.xpath("/descendant::button")).click();
        }

        //hago clic en el carrito
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        //Realizar el Checkout del producto
        WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkout.click();

        //rellenar datos del checkout
        WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
        firstName.sendKeys("manolo");

        WebElement lastName = driver.findElement(By.xpath("//input[@id='last-name']"));
        lastName.sendKeys("perez");

        WebElement postalCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
        postalCode.sendKeys("03680");

        //hacer click a continuar
        WebElement continuar = driver.findElement(By.xpath("//input[@id='continue']"));
        continuar.click();

        //hacer clic en finalizar
        WebElement finalizar = driver.findElement(By.xpath("//button[@id='finish']"));
        finalizar.click();

        //Validar que el precio total del pedido es la suma de los productos seleccionados

    }

    @Test
    public void realizarUnPedido(){
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

        //agregar 1producto al carrito


        //hago clic en el carrito
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

        //Realizar el Checkout del producto
        WebElement checkout = driver.findElement(By.xpath("//button[@id='checkout']"));
        checkout.click();

        //rellenar datos del checkout
        WebElement firstName = driver.findElement(By.xpath("//input[@id='first-name']"));
        firstName.sendKeys("manolo");

        WebElement lastName = driver.findElement(By.xpath("//input[@id='last-name']"));
        lastName.sendKeys("perez");

        WebElement postalCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
        postalCode.sendKeys("03680");

        //hacer click a continuar
        WebElement continuar = driver.findElement(By.xpath("//input[@id='continue']"));
        continuar.click();

        //hacer clic en finalizar
        WebElement finalizar = driver.findElement(By.xpath("//button[@id='finish']"));
        finalizar.click();

        boolean isDisplayed = true;
        String mensaje = "";
        try {
            //Validar que el pedido a finalizado correctamente mostrando el mensaje
            mensaje = driver.findElement(By.xpath("//div[@class='complete-text']")).getText();
        } catch (NoSuchElementException e){
            isDisplayed = false;
        }
        assertTrue("el mensaje no es visible", isDisplayed);
        assertEquals("el mensaje mostrado no es el esperado",mensaje,"Your order has been dispatched, and will arrive just as fast as the pony can get there!");

    }

    @Test
    public void comprobarLogOut(){
        //URL
        String url = "https://www.saucedemo.com/";

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

        //hago clic en el desplegable de las 3 rayas
        WebElement desplegable = driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']"));
        desplegable.click();

        //hago clic en el boton logout
        WebElement botonLogout = driver.findElement(By.xpath("//a[@id='logout_sidebar_link']"));
        botonLogout.click();

        //validar que vuelvo a la página de inicio
        assertEquals("si no va a la pagina de inicio",url, driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        //cerrar navegador
        driver.close();
    }
}
