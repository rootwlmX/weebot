package indi.madoka.weeb.module.plugin;

import indi.madoka.weeb.annotations.Notice;
import indi.madoka.weeb.annotations.Plugin;
import indi.madoka.weeb.bean.send.Sender;
import indi.madoka.weeb.bean.update.notice.UpdateNotice;
import indi.madoka.weeb.enums.NoticeType;
import indi.madoka.weeb.module.service.AntiRecallService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Arcueid
 */
@Plugin("Anti Recall")
@Slf4j
public class AntiRecallPlugin {

    private final AntiRecallService antiRecallService;

    public AntiRecallPlugin(AntiRecallService antiRecallService) {
        this.antiRecallService = antiRecallService;
    }

    @Notice(noticeType = NoticeType.GROUP_RECALL)
    public void antiRecall(UpdateNotice updateNotice){
        String recalledMessage = antiRecallService.getRecallMessageInJsonString(updateNotice);
        new Sender.Builder(recalledMessage).addTextAtBegin("刚刚撤回的消息是\n").build().sendBackJsonInGroup();
    }
}
