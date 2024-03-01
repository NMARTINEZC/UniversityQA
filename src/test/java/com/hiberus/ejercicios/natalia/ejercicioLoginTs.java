package com.hiberus.ejercicios.natalia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ejercicioLoginTs {

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
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");

        //Escribir el password secret_sauce
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");

        //hago clic en el boton login
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

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
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_use");

        //Escribir el password secret_sauce
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");

        //hago clic en el boton login
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        //validar mensaje error
        String mensajeError = "Epic sadface: Username and password do not match any user in this service";


    }

    @After
    public void tearDown() {
        //cerrar navegador
        driver.close();
    }
}
