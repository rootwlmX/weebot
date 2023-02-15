package indi.madoka.weeb.core.module.plugin;

import indi.madoka.weeb.core.annotations.Keyword;
import indi.madoka.weeb.core.annotations.Plugin;
import indi.madoka.weeb.core.bean.send.Sender;
import indi.madoka.weeb.core.bean.update.message.UpdateMessage;
import indi.madoka.weeb.core.enums.MatchType;
import indi.madoka.weeb.core.module.service.SetuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Arcueid
 */
@Plugin
@Slf4j
public class SetuPlugin {
    private final SetuService setuService;

    public SetuPlugin(SetuService setuService, WebClient.Builder builder) {
        this.setuService = setuService;
    }

    @Keyword(value = "#涩图", matchType = MatchType.EQUALS)
    public void randomPicture(UpdateMessage updateMessage) {
        String randomPictureUrl = setuService.getRandomPicture();
        new Sender.Builder(updateMessage).addImage(randomPictureUrl)
                .build()
                .sendAll();
    }
}
