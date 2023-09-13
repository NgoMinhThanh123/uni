/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author acer
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    private String subject;
    private String body;
    private String date;
    private String from;
    private String recipients;
}
