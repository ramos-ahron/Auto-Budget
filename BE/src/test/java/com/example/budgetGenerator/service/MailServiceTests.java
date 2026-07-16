package com.example.budgetGenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

class MailServiceTests {
    private final JavaMailSender sender = mock(JavaMailSender.class);
    private final MailService mailService = new MailService(sender, "from@example.com");

    private MimeMessage send(ThrowingAction action) throws Exception {
        when(sender.createMimeMessage()).thenReturn(new MimeMessage(Session.getInstance(new Properties())));
        action.run();
        ArgumentCaptor<MimeMessage> captor = ArgumentCaptor.forClass(MimeMessage.class);
        verify(sender).send(captor.capture());
        return captor.getValue();
    }

    @Test
    void passwordUpdateUsesItsOwnSubject() throws Exception {
        MimeMessage message = send(() -> mailService.sendPasswordUpdate("user@example.com"));
        assertEquals("Password update", message.getSubject());
    }

    @Test
    void budgetReceiptUsesReceiptSubject() throws Exception {
        MimeMessage message = send(() -> mailService.sendBudgetReceipt("user@example.com", "ok"));
        assertEquals("Budget Receipt", message.getSubject());
    }

    interface ThrowingAction {
        void run() throws Exception;
    }
}
