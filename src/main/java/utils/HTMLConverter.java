/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author acer
 */
public class HTMLConverter {
    public static String convertToHTML(String text) {
        Document document = Jsoup.parseBodyFragment(text);

        return document.body().html();
    }
}
