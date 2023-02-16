package indi.madoka.weeb.enums;

public enum PostType {
    MESSAGE(),
    REQUEST(),
    NOTICE(),
    META_EVENT();

    PostType() {
    }

    public static PostType getPostType(String message){
        switch (message){
            case "meta_event" :
                return META_EVENT;
            case "message":
                return MESSAGE;
            case "request":
                return REQUEST;
            case "notice":
                return NOTICE;
        }
        return null;
    }
}
