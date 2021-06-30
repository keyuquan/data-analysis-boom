package com.analysis.boom.common.utils;

import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;

import java.io.File;

import static io.github.biezhi.ome.OhMyEmail.SMTP_163;

public class EmailUtils {


    public static void sendEmail(String toEmail, String filePath, String fileName) {
        try {
            OhMyEmail.config(SMTP_163(true), "18818406784@163.com", "HQFAEOMLFHMCAGRJ");
            OhMyEmail.subject(fileName)
                    .from("18818406784@163.com")
                    .to(toEmail)
                    .html("<h1 font=red>" + fileName + "</h1>")
                    .attach(new File(filePath), fileName)
                    .send();
        } catch (SendMailException e) {
            e.printStackTrace();
        }
    }


}
