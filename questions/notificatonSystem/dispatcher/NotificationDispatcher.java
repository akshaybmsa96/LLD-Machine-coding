package questions.notificatonSystem.dispatcher;

import questions.notificatonSystem.model.Notification;

public interface NotificationDispatcher {
    void dispatchNotification(Notification notification);
}
