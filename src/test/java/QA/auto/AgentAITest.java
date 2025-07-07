package QA.auto;

import java.net.URI;
import java.util.concurrent.*;
import javax.mail.*;
import javax.mail.internet.*;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.util.Properties;

public class AgentAITest {

    @DataProvider(name = "webSocketData")
    public Object[][] webSocketData() {
        return new Object[][]{
                {"apollo2.humanbrain.in WebSocket", "wss://apollo2.humanbrain.in/aiAgentServer/ws/ai_agent"},
                {"dev2mani.humanbrain.in WebSocket", "wss://apollo2.humanbrain.in/aiAgentServer/ws/ai_agent"}
        };
    }

    @Test(dataProvider = "webSocketData")
    public void testWebSocketConnection(String serverName, String webSocketUrl) throws InterruptedException {
        System.out.println("🔍 Connecting to " + serverName + " → " + webSocketUrl);

        CountDownLatch latch = new CountDownLatch(1);
        final boolean[] success = {false};

        WebSocketClient client = new WebSocketClient(URI.create(webSocketUrl)) {
            private final StringBuilder responseBuffer = new StringBuilder();
            private ScheduledExecutorService scheduler;
            private ScheduledFuture<?> timeoutFuture;

            @Override
            public void onOpen(ServerHandshake handshake) {
                String testMessage = "{"
                        + "\"query\": \"222 1000\","
                        + "\"user\": \"Divya D\","
                        + "\"userId\": 193,"
                        + "\"page\": \"Neurovoyager\","
                        + "\"page_context\": {}"
                        + "}";
                send(testMessage);
                System.out.println("📤 Sent: " + testMessage);

                scheduler = Executors.newSingleThreadScheduledExecutor();
                timeoutFuture = scheduler.schedule(() -> {
                    System.err.println("❌ Timeout (15 seconds) waiting for response from " + serverName);
                    latch.countDown();
                    close();
                }, 15, TimeUnit.SECONDS);
            }

            @Override
            public void onMessage(String message) {
                System.out.println("📥 Received: " + message);
                responseBuffer.append(message);

                if (message.contains("###END")) {
                    if (timeoutFuture != null) timeoutFuture.cancel(true);
                    System.out.println("✅ Full AI Agent response received from " + serverName + ":\n" + responseBuffer.toString());
                    success[0] = true;
                    latch.countDown();
                    close();
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("❌ WebSocket Closed for " + serverName + ". Code: " + code + ", Reason: " + reason);
                if (timeoutFuture != null) timeoutFuture.cancel(true);
                if (scheduler != null) scheduler.shutdownNow();
                latch.countDown();
            }

            @Override
            public void onError(Exception ex) {
                System.err.println("❌ Error in " + serverName + " WebSocket: " + ex.getMessage());
                if (timeoutFuture != null) timeoutFuture.cancel(true);
                if (scheduler != null) scheduler.shutdownNow();
                latch.countDown();
            }
        };

        try {
            client.connectBlocking();
        } catch (Exception e) {
            System.err.println("❌ Could not connect to " + serverName + ": " + e.getMessage());
            sendAlertMail(serverName, e.getMessage());
            return;
        }

        latch.await();

        if (success[0]) {
            System.out.println("✅ Connection to " + serverName + " succeeded.");
        } else {
            sendAlertMail(serverName, "No complete response received.");
        }
    }

    private void sendAlertMail(String serverName, String reason) {
        String[] to = {"sriramv@htic.iitm.ac.in"};
        String[] cc = {"venip@htic.iitm.ac.in", "divya.d@htic.iitm.ac.in", "gayathri@htic.iitm.ac.in"};
        String from = "gayathri@htic.iitm.ac.in";
        String host = "smtp.gmail.com";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("automationsoftware25@gmail.com", "wjzcgaramsqvagxu");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            for (String recipient : to)
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            for (String ccRecipient : cc)
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccRecipient));

            message.setSubject("AI Agent - WebSocket Connection Issue Alert: " + serverName);

            String currentTime = java.time.LocalDateTime.now().toString();
            String content = "<div style='font-family: Arial; font-size: 14px;'>"
                    + "<h3 style='color: #D9534F;'>🚨 AI Agent WebSocket Connection Failure Alert</h3>"
                    + "<p><strong>WebSocket connection to " + serverName + " failed at " + currentTime + ".</strong></p>"
                    + "<p><strong>Reason:</strong> " + reason + "</p>"
                    + "<br><p>Regards,<br><b>Automated Monitoring</b></p></div>";

            message.setContent(content, "text/html");
            Transport.send(message);
            System.out.println("📧 Alert Email sent successfully.");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
