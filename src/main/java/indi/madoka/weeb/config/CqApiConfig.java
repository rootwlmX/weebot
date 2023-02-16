package indi.madoka.weeb.config;

/**
 * @author Arcueid
 */
public class CqApiConfig {
    public static final String CQ_HTTP_REQUEST_URL = "localhost:5700";

    public static final String CQ_HTTP_SEND_GROUP_MSG = CQ_HTTP_REQUEST_URL + "/send_group_msg";

    public static final String CQ_HTTP_SEND_PRIVATE_MSG = CQ_HTTP_REQUEST_URL + "/send_private_msg";

}
