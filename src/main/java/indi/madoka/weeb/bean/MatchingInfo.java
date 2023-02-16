package indi.madoka.weeb.bean;

/**
 * @author Arcueid
 */
public abstract class MatchingInfo {
    protected final Object clazz;
    protected final String plugin;

    public MatchingInfo(Object clazz, String plugin) {
        this.clazz = clazz;
        this.plugin = plugin;
    }

    public Object getClazz() {
        return clazz;
    }

    public String getPlugin() {
        return plugin;
    }

}
