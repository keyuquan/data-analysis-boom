package com.analysis.booms.doris.utils;

import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;

import java.io.File;

import static io.github.biezhi.ome.OhMyEmail.SMTP_163;

public class EmailUtils {


    /**
     * @param toEmail  收件邮箱
     * @param theme    标题
     * @param context  内容
     * @param filePath 附件全路径
     * @param fileName 附件名字
     */
    public static void sendEmail(String toEmail, String theme, String context, String filePath, String fileName) {
        try {
            OhMyEmail.config(SMTP_163(true), "18818406784@163.com", "HQFAEOMLFHMCAGRJ");
            OhMyEmail.subject(theme)
                    .from("18818406784@163.com")
                    .to(toEmail)
                    .html(context)
                    .attach(new File(filePath), fileName)
                    .send();
        } catch (SendMailException e) {
            e.printStackTrace();
        }
    }


}
