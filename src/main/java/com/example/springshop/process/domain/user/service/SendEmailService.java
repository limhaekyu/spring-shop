package com.example.springshop.process.domain.user.service;

import com.example.springshop.process.domain.user.domain.User;
import com.example.springshop.process.domain.user.dto.response.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SendEmailService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "본인이메일주소";

    public MailDto createMailAndChangePassword(String userEmail, String userName){
        String str = getTempPassword();
        MailDto mailDto = new MailDto();
        mailDto.setEmailContents(userEmail, userName+"님의 SpringShop 임시비밀번호 안내 이메일 입니다.",
                "안녕하세요. SpringShop 임시비밀번호 안내 관련 이메일 입니다." + "[" + userName + "]" +"님의 임시 비밀번호는 " + str + " 입니다.");
        updatePassword(str,userEmail, userName);
        return mailDto;
    }

    public void updatePassword(String str,String userEmail, String userName){
        String pw = passwordEncoder.encode(str);
        User user = userService.findByEmailAndUserName(userEmail, userName);
        user.updateUserPw(pw);
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendEmailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
        System.out.println("email 전송 완료");
    }
}
