package com.analysis.boom.common.utils;

import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;

import java.io.File;

import static io.github.biezhi.ome.OhMyEmail.SMTP_QQ;

public class EmailUtils {

    public static void main(String[] args) throws SendMailException {
        OhMyEmail.config(SMTP_QQ(true), "1241504517@qq.com", "Ke15391609819");

        OhMyEmail.subject("这是一封测试附件邮件")
                .from("1241504517@qq.com")
                .to("929842382@qq.com")
                .html("<h1 font=red>日报</h1>")
                .attach(new File("D:\\workspace\\data-analysis-boom\\common\\.gitignore"), "测试")
                .send();

    }

}
