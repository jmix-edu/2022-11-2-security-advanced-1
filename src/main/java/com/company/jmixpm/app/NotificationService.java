package com.company.jmixpm.app;

import com.company.jmixpm.entity.Notification;
import io.jmix.core.UnconstrainedDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class NotificationService {
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;

    @PersistenceContext
    private EntityManager entityManager;

    public void markAsRead(Notification notification) {
        notification = unconstrainedDataManager.load(Notification.class)
                .id(notification.getId())
                .one();

        notification.setIsRead(true);

        unconstrainedDataManager.save(notification);
    }

    @Transactional
    public void markAsReadEm(Notification notification) {
        notification = entityManager.find(Notification.class, notification.getId());
        notification.setIsRead(true);
    }
}