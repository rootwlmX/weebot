package indi.madoka.weeb.module.plugin;

import indi.madoka.weeb.annotations.Keyword;
import indi.madoka.weeb.annotations.Plugin;
import indi.madoka.weeb.bean.update.message.UpdateMessage;
import indi.madoka.weeb.bean.send.Sender;
import indi.madoka.weeb.enums.MatchType;
import indi.madoka.weeb.module.service.SetuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Arcueid
 */
@Plugin("Picture Searcher")
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

    @Keyword(value = "#涩图", matchType = MatchType.STARTS_WITH)
    public void randomPictureByTag(UpdateMessage updateMessage){

    }
}
