import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServerAddressAndPort implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;

    public String getUrl() throws UnknownHostException {
        InetAddress address = null;
        address = InetAddress.getLocalHost();
        return "http://" + address.getHostAddress() + ":" + this.serverPort;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }

}