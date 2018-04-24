package es.ua.dlsi.master.sesion4;

import java.util.prefs.Preferences;

/**
 * @autor drizo
 */
public class GUIPreferences {
    private final Preferences preferences;

    public GUIPreferences() {
        preferences = Preferences.userNodeForPackage(GUIPreferences.class);
    }

    public String getProperty(String name) {
        return preferences.get(name, null);
    }

    public void storeProperty(String name, String value) {
        preferences.put(name, value);
    }
}
