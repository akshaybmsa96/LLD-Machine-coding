package questions.notificatonSystem.service;

import questions.notificatonSystem.model.NotificationType;

import java.util.List;
import java.util.Map;

public class UserPreferenceService {
    Map<String, List<NotificationType>> userPreferences;

    public UserPreferenceService(Map<String, List<NotificationType>> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public void addPreferences(String user, List<NotificationType> types){
        userPreferences.put(user, types);
    }

    public Map<String, List<NotificationType>> getUserPreferences() {
        return userPreferences;
    }
}
