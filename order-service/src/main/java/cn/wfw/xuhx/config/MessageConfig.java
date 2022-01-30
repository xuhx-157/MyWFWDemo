package cn.wfw.xuhx.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//ConfigurationProperties注解可以注入配置文件属性值，只需前缀与该类下的属性值相结合是配置文件的key即可，并且可以实现实时感知，比@Value注入要强大
@ConfigurationProperties(prefix = "message")
public class MessageConfig {

    private Boolean switchStatus;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(Boolean switchStatus) {
        this.switchStatus = switchStatus;
    }
}
