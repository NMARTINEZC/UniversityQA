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

public class ejercicios {
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
    public void ej1() {
        driver.get("https://www.saucedemo.com/");

        String title = driver.getTitle();
        System.out.println("Título de la página: " + title);
        System.out.println("La longitud del título de la página es: " + title.length());

        String url = driver.getCurrentUrl();
        if (url.equals("https://www.saucedemo.com/")) System.out.println("La url obtenida es la esperada: " + url);
        else System.out.println("La url obtenida no es la esperada");

        String pageSource = driver.getPageSource();
        System.out.println("Código de la página: " + pageSource);
        System.out.println("La longitud del código de la página es: " +pageSource.length());

    }

    @Test
    public void ej2() {
        //url
        String url = "https://www.hiberus.com/";

        //Abra el sitio web “https://www.hiberus.com/”.
        driver.get(url);

        //hago clic en permitir cookies
        driver.findElement(By.xpath("//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")).click();

        //Haga click en el enlace de Consultoría y Estrategia usando “driver.findElement(By.xpath(“//a[@href=’/consultoría y estrategia de
        //negocio’]”)).click(); click();”
        driver.findElement(By.xpath("//ul[@class='menu--colossal menu--main_menu']/child::li[1]")).click();

        //Vuelva a la página de inicio (utilice el comando 'Back')
        driver.navigate().back();

        //Vuelva nuevamente a la página de Consultoría y Estrategia (esta vez use el comando 'Forward')
        driver.navigate().forward();

        //Vuelva nuevamente a la página de inicio (esta vez use el comando 'To')
        driver.navigate().to(url);

        //Actualizar el navegador (Use el comando 'Refresh')
        driver.navigate().refresh();
    }

    @After
    public void tearDown() {
        //cerrar navegador
        driver.close();
    }
}
