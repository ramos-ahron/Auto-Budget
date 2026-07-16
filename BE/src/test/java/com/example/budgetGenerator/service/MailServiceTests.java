package com.example.budgetGenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

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

    @Test
    void budgetReceiptEscapesHtmlButKeepsMarkdownFormatting() throws Exception {
        MimeMessage message = send(() -> mailService.sendBudgetReceipt(
            "user@example.com", "**Total** <a href=\"https://evil.example\">click</a>"));
        String html = (String) ((MimeMultipart) ((MimeMultipart) message.getContent())
            .getBodyPart(0).getContent()).getBodyPart(0).getContent();
        assertTrue(html.contains("<strong>Total</strong>"));
        assertTrue(html.contains("&lt;a href="));
        assertFalse(html.contains("<a href="));
    }

    interface ThrowingAction {
        void run() throws Exception;
    }
}
